package com.linbinghui.csdn.util.connectionpool;

import java.sql.Connection;

/**
 * 数据源管理器
 */
public class DataSourceManager {

    static DataSourceConfig dataSourceConfig=new DataSourceConfig();
    static ConnectionPool connectionPool=new ConnectionPool(dataSourceConfig);

    /**
     * 从连接池中获取一个连接对象
     */
    public static Connection getConn(){
        return connectionPool.getConn();
    }

    /**
     * 关闭一个数据库连接(伪关闭)
     */
    public static void close(Connection connection){
        connectionPool.releaseConn(connection);
    }
}
