package com.linbinghui.csdn.util.connectionpool;

import java.sql.Connection;

public class TestConnectionPool {

    public static void main(String[] args) {
        Thead1 t = new Thead1();
        for (int i = 0; i < 8; i++) {
            new Thread(t, "线程" + i).start();
        }
    }
}

/**
 *  普通的类
 */
class Thead1 implements Runnable {
    public void run() {
        try {
            //获取连接
            Connection conn = DataSourceManager.getConn();
            // 模拟正在拿到这连接执行数据库操作
            Thread.sleep(10000);
            //关闭连接
            DataSourceManager.close(conn);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}