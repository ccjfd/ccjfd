package com.linbinghui.csdn.entity;

public class Admin {
    private int id;
    private String adminname;
    private String adminpassword;

    public Admin() {
    }

    public Admin(int id, String adminname, String adminpassword) {
        this.id = id;
        this.adminname = adminname;
        this.adminpassword = adminpassword;
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
     * @return adminname
     */
    public String getAdminname() {
        return adminname;
    }

    /**
     * 设置
     * @param adminname
     */
    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    /**
     * 获取
     * @return adminpassword
     */
    public String getAdminpassword() {
        return adminpassword;
    }

    /**
     * 设置
     * @param adminpassword
     */
    public void setAdminpassword(String adminpassword) {
        this.adminpassword = adminpassword;
    }

    public String toString() {
        return "Admin{id = " + id + ", adminname = " + adminname + ", adminpassword = " + adminpassword + "}";
    }
}
