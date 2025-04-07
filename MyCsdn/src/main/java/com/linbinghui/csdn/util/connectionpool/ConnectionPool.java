package com.linbinghui.csdn.util.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 连接池的实现类
 */
public class ConnectionPool implements IConnectionPool {

    /**
     * 记录连接数的总数(计数器 初始值0)
     */
    AtomicInteger connectionCount = new AtomicInteger(0);

    /**
     * 数据库的配置信息
     */
    private DataSourceConfig dataSourceConfig;

    /**
     * 声明一个线程安全的集合list集合，存储空闲的连接
     * ArrayList线程不安全
     * Vector线程安全
     */
    Vector<Connection> freePools = new Vector<Connection>();

    /**
     * 声明一个线程安全的集合list集合，存储正在使用的连接对象
     * ArrayList线程不安全
     * Vector线程安全
     */
    Vector<ConnectionEntity> usePools = new Vector<ConnectionEntity>();


    public ConnectionPool(DataSourceConfig dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
        //初始化连接池
        init();
    }

    /**
     * 连接池的连接初始化
     */
    private void init() {
        for (int i = 0; i < Integer.valueOf(dataSourceConfig.initSize); i++) {
            Connection connection = createConnection();
            System.out.println("连接池初始化,连接对象:" + connection);
            freePools.add(connection);
        }
        //开启了健康检查，防止连接池连接对象是佛超时 导致连接一直不释放
        if(Boolean.valueOf(dataSourceConfig.getHealth())==true){
            checkConnectionTimeOut();
        }
    }

    /**
     * 定时检查占用时间超长的连接，并关闭   Quartz、Spring Task、xxlJob第三方定时任务框架！
     */
    private void checkConnectionTimeOut() {
        Worker worker = new Worker();
        //调度任务,此时的值 启动完毕后2秒开始后台检测，每间隔2秒检测一回
        new Timer().schedule(worker, Long.valueOf(dataSourceConfig.getDelay()), Long.valueOf(dataSourceConfig.getPeriod()));
    }

    /**
     * 局部内部类-任务类
     */
    class Worker extends TimerTask {

        public void run() {
            System.out.println("定时检查占用时间超长的连接Connection并关闭...");
            try {
                //遍历正在使用的连接池
                for (int i=0;i<usePools.size();i++) {
                    ConnectionEntity connectionEntity = usePools.get(i);
                    if((System.currentTimeMillis()-connectionEntity.getUseStartTime()>Long.valueOf(dataSourceConfig.getTimeout()))){
                        Connection connection = connectionEntity.getConnection();
                        //有一个conn对象使用超过了设置的超时时间
                        if(isAvailable(connection)){
                            connection.close();
                            usePools.remove(i);
                            //连接总数-1
                            connectionCount.decrementAndGet();
                            System.out.println(Thread.currentThread().getName()+"定时检查占用时间超长的连接Connection:" + connection + "直接关闭删除,空闲连接池大小是:" + freePools.size() + ",正在使用的连接池大小是:" + usePools.size() + ",总连接数是:" + connectionCount);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
            }
        }
    }


    /**
     * 创建连接池
     *
     * @return
     */
    private synchronized Connection createConnection() {
        Connection connection = null;
        try {
            Class.forName(dataSourceConfig.getDriver());
            connection = DriverManager.getConnection(dataSourceConfig.getUrl(), dataSourceConfig.getUsername(), dataSourceConfig.getPassword());
            //累加+1
            connectionCount.incrementAndGet();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return connection;
    }

    /**
     * 从连接池获取一个连接对象
     *
     * @return
     */
    public synchronized Connection getConn() {
        long timeMillis = System.currentTimeMillis();
        Connection connection = null;
        try {
            //判断空闲的连接池中是否还有连接
            if (!freePools.isEmpty()) {
                //空闲连接池非空
                connection = freePools.get(0);
                if (isAvailable(connection)) {
                    freePools.remove(connection);
                    //加入到正在使用的连接池中
                    ConnectionEntity connectionEntity = new ConnectionEntity(connection,timeMillis);
                    usePools.add(connectionEntity);
                }
                System.out.println(Thread.currentThread().getName() + "从空闲连接池获取了一个连接:" + connection + ",空闲连接池大小是:" + freePools.size() + ",正在使用的连接池大小是:" + usePools.size() + ",总连接数是:" + connectionCount);
            } else {
                //判断空闲的连接池中已经没有连接啦，新创建一些
                if (connectionCount.get() < Integer.valueOf(dataSourceConfig.getMaxSize())) {
                    connection = createConnection();
                    //加入到正在使用的连接池中
                    ConnectionEntity connectionEntity = new ConnectionEntity(connection, timeMillis);
                    usePools.add(connectionEntity);
                    System.out.println(Thread.currentThread().getName() + "连接池没有空闲连接，创建一个新的:" + connection + ",空闲连接池大小是:" + freePools.size() + ",正在使用的连接池大小是:" + usePools.size() + ",总连接数是:" + connectionCount);
                } else {
                    System.out.println(Thread.currentThread().getName() + "连接数已经超了总大小，进行等待! 空闲连接池大小是:" + freePools.size() + ",正在使用的连接池大小是:" + usePools.size() + ",总连接数是:" + connectionCount);
                    // 等待空闲的连接对象
                    this.wait(Integer.valueOf(dataSourceConfig.getWaittime()));
                    //获取连接对象重试
                    connection = getConn();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 判断这个连接是否可用
     *
     * @param connection
     * @return
     */
    private boolean isAvailable(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 归还连接对象
     */
    public synchronized void releaseConn(Connection connection) {
        if (isAvailable(connection)) {
            //如果这个连接可用，放入空闲连接池中
            freePools.add(connection);

            //从正在使用的连接池中移除这个连接
            for (int i = 0; i < usePools.size(); i++) {
                Connection conn = usePools.get(i).getConnection();
                if (conn == connection) {
                    usePools.remove(i);
                }
            }
            System.out.println(Thread.currentThread().getName() + "归还了一个连接对象:" + connection + ",空闲连接池大小是:" + freePools.size() + ",正在使用的连接池大小是:" + usePools.size() + ",总连接数是:" + connectionCount);
        }
        this.notifyAll();
    }
}
