package com.linbinghui.csdn.entity;

public class Follow {
    private int id;
    private int followerid;
    private int followedid;

    public Follow() {
    }

    public Follow(int id, int followerid, int followedid) {
        this.id = id;
        this.followerid = followerid;
        this.followedid = followedid;
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
     * @return followerid
     */
    public int getFollowerid() {
        return followerid;
    }

    /**
     * 设置
     * @param followerid
     */
    public void setFollowerid(int followerid) {
        this.followerid = followerid;
    }

    /**
     * 获取
     *
     * @return followedid
     */
    public int getFollowedid() {
        return followedid;
    }

    /**
     * 设置
     * @param followedid
     */
    public void setFollowedid(int followedid) {
        this.followedid = followedid;
    }

    public String toString() {
        return "Follow{id = " + id + ", followerid = " + followerid + ", followedid = " + followedid + "}";
    }
}
