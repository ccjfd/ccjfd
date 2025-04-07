package com.linbinghui.csdn.service;

import com.linbinghui.csdn.controller.CurrentUser;
import com.linbinghui.csdn.dao.BlogMapper;
import com.linbinghui.csdn.entity.Blog;

import java.util.ArrayList;
import java.util.List;

public class myBlogService {
private static BlogMapper blogMapper = null;

public myBlogService(BlogMapper blogMapper) {this.blogMapper = blogMapper;}

//查看我的博文(粗略的)
    public static List<Blog> showMyBlog() {
    Blog b = new Blog();
    b.setUserid(CurrentUser.getCurrentUser().getId());
       List<Blog> blog = blogMapper.selectBlogByUId(b);
        if (blog.get(0) != null) {
            return blog;
        } else {
            return null;
            /*System.out.println("您还没有发布过博文!");*/
        }
    }

    // 发布博文
public static void InsertBlog(String title, String content) {
    Blog blog = new Blog();
    blog.setUserid(CurrentUser.getCurrentUser().getId());
    blog.setTitle(title);
    blog.setContent(content);
    blog.setColumnid(0);
    /*blog.setBlogdate();*/
    blog.setCommentnum(0);
    blog.setLikenum(0);
    blog.setAgainstnum(0);
    blog.setOrder(0);
    blogMapper.insert(blog);
}
}
