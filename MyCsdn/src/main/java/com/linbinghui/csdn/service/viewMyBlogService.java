package com.linbinghui.csdn.service;

import com.linbinghui.csdn.controller.CurrentUser;
import com.linbinghui.csdn.dao.*;
import com.linbinghui.csdn.entity.*;
import com.linbinghui.csdn.view.viewMyBlogView;

import java.util.List;
import java.util.Scanner;

import static com.linbinghui.csdn.controller.CurrentUser.getCurrentUser;



public class viewMyBlogService {
    private static UserMapper userMapper ;
    private static CommentMapper commentMapper;
    private static BlogMapper blogMapper;
    private static LikecommentMapper likecommentMapper ;
    private static ColumnMapper columnMapper ;
    public viewMyBlogService(CommentMapper commentMapper, UserMapper userMapper, BlogMapper blogMapper, LikecommentMapper likecommentMapper, ColumnMapper columnMapper) {
        this.commentMapper = commentMapper;
        this.userMapper = userMapper;
        this.blogMapper = blogMapper;
        this.likecommentMapper = likecommentMapper;
        this.columnMapper = columnMapper;
    }
    private static final Scanner scanner = new Scanner(System.in);
    static viewMyBlogView viewmyblogview = new viewMyBlogView();




    //展示我想要看的我的博文
    public static Blog putMyBlog(String blogTitle,List<Blog> mybloglist) {
        Blog thisblog = new Blog();
        for (Blog blog : mybloglist) {
            //比较标题内容相同
            if (blog.getTitle().equals(blogTitle)) {
                thisblog.setTitle(blog.getTitle());
                Blog myblog = blogMapper.selectBlogByTitle(thisblog);
                return myblog;
            }
        }
        return null;
    }

    //获取博文下的评论
    public static List<Comment> getMyBlogComment(Blog thisblog) {
        Comment comment = new Comment();
        comment.setBlogid(thisblog.getId());
        List<Comment> commentList = commentMapper.selectCommentByBlogId(comment);
        return commentList;
    }
    //获取评论用户名
    public static String getCommentUserName(int userid) {
        User u=new User();
        u.setId(userid);
        User user = userMapper.selectById(u);
        return user.getName();
    }
    //编辑博文标题
    public static void editMyBlogTitleservice(String newTitle, Blog thisblog) {
        Blog blog = new Blog();
        blog.setId(thisblog.getId());
        blog.setTitle(newTitle);
        blogMapper.updateBlogTitle(blog);
    }
    //编辑博文内容
    public static void editMyBlogContentservice(String newContent, Blog thisblog) {
        Blog blog = new Blog();
        blog.setId(thisblog.getId());
        blog.setContent(newContent);
        blogMapper.updateBlogContent(blog);
    }
//删除博文
    public static void deleteMyBlogservice(Blog myblog) {
        Blog blog = new Blog();
        blog.setId(myblog.getId());
        blogMapper.deleteBlog(blog);
    }
//发表评论
    public static void publishComment(String commentContent, Blog myblog) {
        if (CurrentUser.getCurrentUser().getStatus().equals("active")){
            Comment comment = new Comment();
            comment.setUserid(myblog.getUserid());
            comment.setBlogid(myblog.getId());
            comment.setContent(commentContent);
            /*comment.setCommentdate(String.valueOf(System.currentTimeMillis()));*/
            comment.setLikenum(0);
            commentMapper.insert(comment);
            viewmyblogview.success();
        }else{
            viewmyblogview.errorComment();
        }
    }
    //删除评论
    public static void deleteComment(int commentid) {
        Comment c = new Comment();
        c.setId(commentid);
        Comment comment = commentMapper.selectBycommentid(c);
        commentMapper.delete(comment);
    }
    //点赞评论
    public static void likeComment(int commentid) {
        Likecomment Lc = new Likecomment();
        Lc.setLikedcommentid(commentid);
        List<Likecomment> likecomments = likecommentMapper.selectBylikedcomment(Lc);
        if (likecomments.size() > 0) {
            for (Likecomment likecomment : likecomments) {
                if (likecomment.getLikerid() == getCurrentUser().getId()) {
                    viewmyblogview.errorlike();
                    return;
                }
            }
        }
        Likecomment likecomment = new Likecomment();
        likecomment.setLikedcommentid(commentid);
        likecomment.setLikerid(getCurrentUser().getId());
        likecommentMapper.insert(likecomment);
        Comment comment = new Comment();
        comment.setId(commentid);
        Comment thiscomment = commentMapper.selectBycommentid(comment);
        thiscomment.setLikenum(thiscomment.getLikenum() + 1);
        commentMapper.updateLikenum(thiscomment);
        viewmyblogview.success();
    }
    //取消点赞评论
    public static void unlikeComment(int commentid) {
        Likecomment Lc = new Likecomment();
        Lc.setLikedcommentid(commentid);
        List<Likecomment> likecomments = likecommentMapper.selectBylikedcomment(Lc);
        if (likecomments.size() > 0) {
            for (Likecomment likecomment : likecomments) {
                if (likecomment.getLikerid() == getCurrentUser().getId()) {
                    likecommentMapper.delete(likecomment);
                    Comment comment = new Comment();
                    comment.setId(commentid);
                    Comment thiscomment = commentMapper.selectBycommentid(comment);
                    thiscomment.setLikenum(thiscomment.getLikenum() - 1);
                    commentMapper.updateLikenum(thiscomment);
                    viewmyblogview.success();
                    return;
                }
            }
        }
        viewmyblogview.errorunlike();
    }
    //将博文添加到专栏
    public static void addMyBlogToColumn(String columnName, Blog thisblog) {
        if (thisblog.getColumnid()==0){//如果该博文没有添加到专栏中
            Column c = new Column();
            c.setColumnname(columnName);
            Blog blog = new Blog();
            blog.setId(thisblog.getId());
            blog.setColumnid(columnMapper.selectByColumnName(c).getId());
            blogMapper.updateBlogColumnid(blog);
            viewmyblogview.success();
        }else{//更改专栏
            viewmyblogview.changeBlogtoColumnView();
            int choice = scanner.nextInt();
            if (choice == 2){
                return;
            }
            Column c = new Column();
            c.setColumnname(columnName);
            Blog blog = new Blog();
            blog.setId(thisblog.getId());
            blog.setColumnid(columnMapper.selectByColumnName(c).getId());
            blogMapper.updateBlogColumnid(blog);
            viewmyblogview.success();
        }
    }
    //姜博文从专栏中移除
    public static void removeMyBlogFromColumn(Blog thisblog) {
        if (thisblog.getColumnid() != 0) {
            thisblog.setColumnid(0);
            blogMapper.updateBlogColumnid(thisblog);
            viewmyblogview.success();
        } else {
            viewmyblogview.errorDeleteColumn();
        }
    }
}
