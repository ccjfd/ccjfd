package com.linbinghui.csdn.entity;

public class Likecomment {
    private int id;
    private int likerid;
    private int likedcommentid;

    public Likecomment() {
    }

    public Likecomment(int id, int likerid, int likedcommentid) {
        this.id = id;
        this.likerid = likerid;
        this.likedcommentid = likedcommentid;
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
     * @return likedcommentid
     */
    public int getLikedcommentid() {
        return likedcommentid;
    }

    /**
     * 设置
     * @param likedcommentid
     */
    public void setLikedcommentid(int likedcommentid) {
        this.likedcommentid = likedcommentid;
    }

    public String toString() {
        return "Likecomment{id = " + id + ", likerid = " + likerid + ", likedcommentid = " + likedcommentid + "}";
    }
}
