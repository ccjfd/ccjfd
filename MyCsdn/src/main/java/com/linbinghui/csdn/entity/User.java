package com.linbinghui.csdn.entity;

public class User {
    private int id;
    private String name;
    private String password;
    private String status;

    public User() {
    }

    public User(int id,String name, String password,String status) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.status = status;
    }


    /**
     * 获取
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     * @return isadmin
     */

    /**
     * 获取
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return "User{id = " + id + ", name = " + name + ", password = " + password + ", status = " + status + "}";
    }
}
