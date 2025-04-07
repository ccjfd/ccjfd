package com.linbinghui.csdn.util.connectionpool;

import javax.sql.DataSource;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

/**
 * 读取数据库配置文件，封装一个配置类
 */
public class DataSourceConfig implements DataSource{

    private String driver;
    private String url;
    private String username;
    public String password;

    public String initSize = "3";
    public String maxSize = "6";
    public String health = "true";
    private String delay = "1000";
    private String period = "1000";
    private String waittime = "1000";
    private String timeout = "500000";

    public DataSourceConfig() {
        try {
            Properties properties = new Properties();
            properties.load(DataSourceConfig.class.getClassLoader().getResourceAsStream("db.properties"));
            Set<Object> keySet = properties.keySet();
            for (Object key : keySet) {
                String fieldName = key.toString().split("\\.")[1];
                String filedValue = properties.getProperty(key.toString());

                Field field = this.getClass().getDeclaredField(fieldName);

                //field.setAccessible(true);
                //field.set(this,filedValue);

                // 反射获取setXxxx方法
                Method method = this.getClass().getDeclaredMethod(toUpper(fieldName), field.getType());
                //反射调用方法赋值
                method.invoke(this, filedValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    // 读取配置文件中的key,并把他转成正确的set方法
    public String toUpper(String fieldName) {
        char[] chars = fieldName.toCharArray();
        chars[0] -= 32;     // 如何把一个字符串的首字母变成大写
        return "set" + new String(chars);
    }

    private DataSource dataSource;


    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInitSize() {
        return initSize;
    }

    public void setInitSize(String initSize) {
        this.initSize = initSize;
    }

    public String getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(String maxSize) {
        this.maxSize = maxSize;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getWaittime() {
        return waittime;
    }

    public void setWaittime(String waittime) {
        this.waittime = waittime;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    @Override
    public String toString() {
        return "DataSourceConfig{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", initSize='" + initSize + '\'' +
                ", maxSize='" + maxSize + '\'' +
                ", health='" + health + '\'' +
                ", delay='" + delay + '\'' +
                ", period='" + period + '\'' +
                ", waittime='" + waittime + '\'' +
                ", timeout='" + timeout + '\'' +
                '}';
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
