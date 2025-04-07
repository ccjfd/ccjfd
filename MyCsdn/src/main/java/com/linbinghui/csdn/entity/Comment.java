package com.linbinghui.csdn.entity;

import java.time.LocalDateTime;

public class Comment {
    private int id;
    private int userid;
    private int blogid;
    private String content;
    private LocalDateTime commentdate;
    private int likenum;

    public Comment() {
    }

    public Comment(int id, int userid, int blogid, String content, LocalDateTime commentdate, int likenum) {
        this.id = id;
        this.userid = userid;
        this.blogid = blogid;
        this.content = content;
        this.commentdate = commentdate;
        this.likenum = likenum;
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
     * @return userid
     */
    public int getUserid() {
        return userid;
    }

    /**
     * 设置
     * @param userid
     */
    public void setUserid(int userid) {
        this.userid = userid;
    }

    /**
     * 获取
     * @return blogid
     */
    public int getBlogid() {
        return blogid;
    }

    /**
     * 设置
     * @param blogid
     */
    public void setBlogid(int blogid) {
        this.blogid = blogid;
    }

    /**
     * 获取
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取
     * @return commentdate
     */
    public LocalDateTime getCommentdate() {
        return commentdate;
    }

    /**
     * 设置
     * @param commentdate
     */
    public void setCommentdate(LocalDateTime commentdate) {
        this.commentdate = commentdate;
    }

    /**
     * 获取
     * @return likenum
     */
    public int getLikenum() {
        return likenum;
    }

    /**
     * 设置
     * @param likenum
     */
    public void setLikenum(int likenum) {
        this.likenum = likenum;
    }

    public String toString() {
        return "Comment{id = " + id + ", userid = " + userid + ", blogid = " + blogid + ", content = " + content + ", commentdate = " + commentdate + ", likenum = " + likenum + "}";
    }
}
