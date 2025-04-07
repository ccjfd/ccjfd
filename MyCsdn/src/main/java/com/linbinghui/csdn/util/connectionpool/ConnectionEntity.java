package com.linbinghui.csdn.util.connectionpool;

import java.sql.Connection;

/**
 * 做一层封装
 */
public class ConnectionEntity {

    private Connection connection;  //连接对象
    private Long useStartTime; //开始使用时间(时间戳)

    public ConnectionEntity(Connection connection, Long useStartTime) {
        this.connection = connection;
        this.useStartTime = useStartTime;
    }

    public ConnectionEntity() {
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Long getUseStartTime() {
        return useStartTime;
    }

    public void setUseStartTime(Long useStartTime) {
        this.useStartTime = useStartTime;
    }
}