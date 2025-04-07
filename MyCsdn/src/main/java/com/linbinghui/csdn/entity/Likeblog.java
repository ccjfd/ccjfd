package com.linbinghui.csdn.entity;

public class Likeblog {
    private int id;
    private int likerid;
    private int likedblogid;

    public Likeblog() {
    }

    public Likeblog(int id, int likerid, int likedblogid) {
        this.id = id;
        this.likerid = likerid;
        this.likedblogid = likedblogid;
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
     * @return likerid
     */
    public int getLikerid() {
        return likerid;
    }

    /**
     * 设置
     * @param likerid
     */
    public void setLikerid(int likerid) {
        this.likerid = likerid;
    }

    /**
     * 获取
     * @return likedblogid
     */
    public int getLikedblogid() {
        return likedblogid;
    }

    /**
     * 设置
     * @param likedblogid
     */
    public void setLikedblogid(int likedblogid) {
        this.likedblogid = likedblogid;
    }

    public String toString() {
        return "Likeblog{id = " + id + ", likerid = " + likerid + ", likedblogid = " + likedblogid + "}";
    }
}
