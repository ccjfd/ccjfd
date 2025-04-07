package com.linbinghui.csdn.util.connectionpool;

import java.sql.Connection;

/**
 * 连接池接口
 */
public interface IConnectionPool {

    /**
     * 从连接池中拿去一个连接对象
     * @return
     */
    Connection getConn();

    /**
     * 把连接对象归还到连接池
     */
    void releaseConn(Connection connection);
}
