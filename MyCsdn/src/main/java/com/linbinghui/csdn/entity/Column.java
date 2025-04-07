package com.linbinghui.csdn.entity;

public class Column {
    private int id;
    private int userid;
    private String columnname;

    public Column() {
    }

    public Column(int id, int userid, String columnname) {
        this.id = id;
        this.userid = userid;
        this.columnname = columnname;
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
     * @return useerid
     */
    public int getUserid() {
        return userid;
    }

    /**
     * 设置
     * @param useerid
     */
    public void setUserid(int useerid) {
        this.userid = useerid;
    }

    /**
     * 获取
     * @return columnname
     */
    public String getColumnname() {
        return columnname;
    }

    /**
     * 设置
     * @param columnname
     */
    public void setColumnname(String columnname) {
        this.columnname = columnname;
    }

    public String toString() {
        return "Column{id = " + id + ", useerid = " + userid + ", columnname = " + columnname + "}";
    }
}
