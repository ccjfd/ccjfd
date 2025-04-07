package com.linbinghui.csdn.service;

import com.linbinghui.csdn.dao.AdminMapper;
import com.linbinghui.csdn.dao.BlogMapper;
import com.linbinghui.csdn.dao.CommentMapper;
import com.linbinghui.csdn.dao.UserMapper;
import com.linbinghui.csdn.entity.Blog;
import com.linbinghui.csdn.entity.Comment;
import com.linbinghui.csdn.entity.User;

import java.util.List;

public class AdminService {
    private AdminMapper adminMapper;
    private BlogMapper blogMapper;
    private CommentMapper commentMapper;
    private UserMapper userMapper;
    public AdminService(AdminMapper adminMapper, BlogMapper blogMapper, CommentMapper commentMapper, UserMapper userMapper)
    {
        this.adminMapper = adminMapper;
        this.blogMapper = blogMapper;
        this.commentMapper = commentMapper;
        this.userMapper = userMapper;
    }

    //返回封禁用户
    public List<User> getbanUser()
    {
        return userMapper.selectBanUser();
    }
    //返回所有博文
    public List<Blog> getAllBlog()
    {
        return blogMapper.selectBlogAgainstnumOrder();
    }
    //根据id查看博文具体内容
    public Blog selectBlogById(int blogid)
    {
        Blog b = new Blog();
        b.setId(blogid);
        return blogMapper.selectBlogById(b);
    }
    //获取评论区下的评论
    public List<Comment> getMyBlogComment(Blog blog) {
        Comment comment = new Comment();
        comment.setBlogid(blog.getId());
        List<Comment> commentList = commentMapper.selectCommentByBlogId(comment);
        return commentList;
    }
    //删除博文
    public void deleteBlog(Blog blog) {
        blogMapper.deleteBlog(blog);
    }
    //删除评论
    public void deleteComment(int commentid) {
        Comment c = new Comment();
        c.setId(commentid);
        Comment comment = commentMapper.selectBycommentid(c);
        commentMapper.delete(comment);
    }
    //封禁用户
    public boolean banUser(int userid) {
        User u= new User();
        u.setId(userid);
        User user = userMapper.selectById(u);
        if(user.getStatus().equals("ban")){
            return false;
        }
        user.setStatus("ban");
        userMapper.updateUserStatus(user);
        return true;
    }
    //解封用户
    public boolean nobanUser(int userid) {
        User u= new User();
        u.setId(userid);
        User user = userMapper.selectById(u);
        if (user.getStatus().equals("active")){
            return false;
        }
        user.setStatus("active");
        userMapper.updateUserStatus(user);
        return true;
    }
}
