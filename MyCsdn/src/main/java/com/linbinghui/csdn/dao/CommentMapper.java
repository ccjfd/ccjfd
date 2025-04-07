package com.linbinghui.csdn.dao;

import com.linbinghui.csdn.entity.Comment;

import java.util.List;

public interface CommentMapper {
    List<Comment> list();
    List<Comment> selectCommentByBlogId(Comment comment);
    Comment selectBycommentid(Comment comment);
    //发表评论
    Integer insert(Comment comment);
    //删除评论
    Integer delete(Comment comment);
    //更新评论点赞数
    Integer updateLikenum(Comment comment);
}
