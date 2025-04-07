package com.linbinghui.csdn.controller;

import com.linbinghui.csdn.entity.Blog;
import com.linbinghui.csdn.entity.Comment;
import com.linbinghui.csdn.service.viewMyBlogService;
import com.linbinghui.csdn.view.viewMyBlogView;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.in;

public class viewMyBlogController {
    private static final Scanner scanner = new Scanner(System.in);

    private static viewMyBlogService viewmyblogservice=null;
    public viewMyBlogController(viewMyBlogService viewmyblogservice) {this.viewmyblogservice = viewmyblogservice;}

    static viewMyBlogView viewmyblogview = new viewMyBlogView();
    public static void viewMyBlogOperater(List<Blog> myblog) {
        if (myblog==null){
            return;
        }else{
            while (true){
                viewmyblogview.viewMyBlogMenu();
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:// 查看我的博文详情
                        checkMyBlog(myblog);
                        break;
                    case 2://退出
                        return;
                    default:
                        viewmyblogview.error();
                        break;
                }
            }
        }
    }

    // 查看我的博文详情
    private static void checkMyBlog(List<Blog> mybloglist) {
    viewmyblogview.myBlogTextDetailsview();
    String blogTitle = scanner.next();
    Blog myblog = viewmyblogservice.putMyBlog(blogTitle, mybloglist);


        if (myblog != null){
            List<Comment> commentlist = viewmyblogservice.getMyBlogComment(myblog);

            //展示博文，并给予操作选项
            viewmyblogview.ShowmyBlogTextDetails(myblog, commentlist);
            operAboutMyBlog(myblog, commentlist);
        }else{
            viewmyblogview.errorTitle();
        }
    }

    //给予操作选项
    public static void operAboutMyBlog(Blog myblog, List<Comment> commentlist) {
    while (true){
        viewmyblogview.operMyBlogView();
        int choice = scanner.nextInt();
        switch (choice) {
            case 1://编辑博文
            editMyBlog(myblog);
                break;
            case 2://删除博文
            deleteMyBlog(myblog);
                break;
            case 3://发表评论
            publishComment(myblog);
                break;
            case 4://删除评论
            deleteComment();
                break;
            case 5://点赞评论
            likeComment();
                break;
            case 6://取消点赞
            unlikeComment();
                break;
            case 7://添加博文到专栏
            addBlogtoColumn(myblog);
                break;
            case 8://将博文从专栏中移除
            viewmyblogservice.removeMyBlogFromColumn(myblog);
                break;
            case 9://返回
                return;
            default:
                viewmyblogview.error();
                break;
        }
    }
    }
//编辑博文
    public static void editMyBlog(Blog myblog) {
        viewmyblogview.editMyBlogchoice();
        int choice = scanner.nextInt();
        switch (choice){
            case 1://编辑博文标题
                editMyBlogTitle(myblog);
                break;
            case 2://编辑博文内容
                editMyBlogContent(myblog);
                break;
            case 3://返回
                return;
            default:
                viewmyblogview.error();
                break;
        }
    }
//编辑博文标题
    public static void editMyBlogTitle(Blog myblog) {
        String newTitle = scanner.next();
        viewmyblogservice.editMyBlogTitleservice(newTitle,myblog);
        viewmyblogview.success();
    }
//编辑博文内容
    public static void editMyBlogContent(Blog myblog) {
        String newContent = scanner.next();
        viewmyblogservice.editMyBlogContentservice(newContent,myblog);
        viewmyblogview.success();
    }
//删除博文
    public static void deleteMyBlog(Blog myblog) {
        viewmyblogservice.deleteMyBlogservice(myblog);
        viewmyblogview.success();
    }
//发表评论
    public static void publishComment(Blog myblog) {
        viewmyblogview.publishComment();
        String commentContent = scanner.next();
        viewmyblogservice.publishComment(commentContent,myblog);
    }
    // 删除评论
    public static void deleteComment() {
    viewmyblogview.deleteComment();
    int commentid = scanner.nextInt();
    viewmyblogservice.deleteComment(commentid);
    viewmyblogview.success();
    }
//点赞评论
    public static void likeComment() {
        viewmyblogview.likeComment();
        int commentid = scanner.nextInt();
        viewmyblogservice.likeComment(commentid);
    }
//取消点赞
    public static void unlikeComment() {
        viewmyblogview.unlikeComment();
        int commentid = scanner.nextInt();
        viewmyblogservice.unlikeComment(commentid);
    }
//添加博文到专栏
    public static void addBlogtoColumn(Blog myblog) {
        viewmyblogview.printSeparator();
        viewmyblogview.addBlogtoColumnView();
        String columnName = scanner.next();
        viewmyblogservice.addMyBlogToColumn(columnName,myblog);
    }
}
