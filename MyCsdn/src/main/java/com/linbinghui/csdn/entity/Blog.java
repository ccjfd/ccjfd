package com.linbinghui.csdn.entity;

import java.time.LocalDateTime;

public class Blog {
    private int id;
    private int userid;
    private int columnid;
    private String title;
    private String content;
    private LocalDateTime blogdate;
    private int commentnum;
    private int likenum;
    private int againstnum;
    private int order;


    public Blog() {
    }

    public Blog(int id, int userid, int columnid, String title, String content, LocalDateTime blogdate, int commentnum, int likenum, int againstnum, int order) {
        this.id = id;
        this.userid = userid;
        this.columnid = columnid;
        this.title = title;
        this.content = content;
        this.blogdate = blogdate;
        this.commentnum = commentnum;
        this.likenum = likenum;
        this.againstnum = againstnum;
        this.order = order;
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
     * @return columnid
     */
    public int getColumnid() {
        return columnid;
    }

    /**
     * 设置
     * @param columnid
     */
    public void setColumnid(int columnid) {
        this.columnid = columnid;
    }

    /**
     * 获取
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
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
     * @return blogdate
     */
    public LocalDateTime getBlogdate() {
        return blogdate;
    }

    /**
     * 设置
     * @param blogdate
     */
    public void setBlogdate(LocalDateTime blogdate) {
        this.blogdate = blogdate;
    }

    /**
     * 获取
     * @return commentnum
     */
    public int getCommentnum() {
        return commentnum;
    }

    /**
     * 设置
     * @param commentnum
     */
    public void setCommentnum(int commentnum) {
        this.commentnum = commentnum;
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

    /**
     * 获取
     * @return againstnum
     */
    public int getAgainstnum() {
        return againstnum;
    }

    /**
     * 设置
     * @param againstnum
     */
    public void setAgainstnum(int againstnum) {
        this.againstnum = againstnum;
    }

    /**
     * 获取
     * @return order
     */
    public int getOrder() {
        return order;
    }

    /**
     * 设置
     * @param order
     */
    public void setOrder(int order) {
        this.order = order;
    }

    public String toString() {
        return "Blog{id = " + id + ", userid = " + userid + ", columnid = " + columnid + ", title = " + title + ", content = " + content + ", blogdate = " + blogdate + ", commentnum = " + commentnum + ", likenum = " + likenum + ", againstnum = " + againstnum + ", order = " + order + "}";
    }
}
