package com.linbinghui.csdn.dao;

import com.linbinghui.csdn.entity.Blog;

import java.util.List;

public interface BlogMapper {
    //查询所有博文
    List<Blog> selectlist();
    //发布博文
    Integer insert(Blog blog);
    //查询博文

    //通过博文ID查询博文
    Blog selectBlogById(Blog blog);
    //查询博文
    List<Blog> selectBlogByUId(Blog blog);
    List<Blog> selectBlogByColumnId(Blog blog);
    List<Blog> selectBlogAgainstnumOrder();
    Blog selectBlogByTitle(Blog blog);
    //编辑博文标题
    String updateBlogTitle(Blog blog);
    //编辑博文内容
    String updateBlogContent(Blog blog);
    //更新博文点赞数
    String updateBlogLike(Blog blog);
    //更新博文举报数
    String updateBlogReport(Blog blog);
    //编辑博文专栏
    String updateBlogColumnid(Blog blog);
    //更新博文置顶状态
    String updateBlogOrder(Blog blog);
    //删除博文
    String deleteBlog(Blog blog);
}
