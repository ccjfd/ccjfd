package com.linbinghui.csdn.view;

import com.linbinghui.csdn.entity.Blog;
import com.linbinghui.csdn.entity.Comment;

import java.util.List;
import java.util.Scanner;

import static com.linbinghui.csdn.service.viewMyBlogService.getCommentUserName;

public class viewMyBlogView {

    public static void viewMyBlogMenu()
    {
        printSeparator();
        System.out.println("1.查看博文详情：");
        System.out.println("2.返回");
        printSeparator();
        System.out.print("请选择操作: ");
    }
    //选择要查看的博文详情
public static void myBlogTextDetailsview(){
    printSeparator();
    System.out.println("输入您想查看的博文的标题：");
    printSeparator();
}
//展示我的博文详情
    public static void ShowmyBlogTextDetails(Blog myblog, List<Comment> commentlist){
        printSeparator();
        System.out.println("博文标题：" + myblog.getTitle());
        System.out.println("博文内容：" + myblog.getContent());
        System.out.println("发布时间：" + myblog.getBlogdate());
        printSeparator();
        if (commentlist!=null){
            ShowComment(commentlist);
        }else if (commentlist.size()==0){
            System.out.println("该博文暂无评论！");
            printSeparator();
        }

    }
    //评论区展示
    public static void ShowComment(List<Comment> commentlist){
        printSeparator();
        for (Comment comment : commentlist) {
            String commentUserName=getCommentUserName(comment.getUserid());
            System.out.println("评论ID：" + comment.getId());
            System.out.println(commentUserName + " ：" + comment.getContent());
            System.out.println("评论时间：" + comment.getCommentdate());
            System.out.println("点赞数：" + comment.getLikenum());
            System.out.println("--------------------");
        }
        printSeparator();
    }
    //展示玩我的博文后可执行的操作选项
    public static void operMyBlogView()
    {
        printSeparator();
        System.out.println("1.编辑博文");
        System.out.println("2.删除博文");
        System.out.println("3.发表评论：");
        System.out.println("4.删除评论：");
        System.out.println("5.点赞评论：");
        System.out.println("6.取消点赞");
        System.out.println("7.添加博文到专栏");
        System.out.println("8.从专栏移除该博文");
        System.out.println("9.返回");
        printSeparator();
        System.out.print("请选择操作: ");
    }

    //编辑博文
    public static void editMyBlogchoice(){
        printSeparator();
        System.out.println("1.编辑博文的标题：");
        System.out.println("2.编辑博文的内容：");
        System.out.println("3.返回");
        printSeparator();
    }
    //发表评论
    public static void publishComment() {
    System.out.println("输入您想评论的内容：");
    }
    //删除评论
    public static void deleteComment() {
        System.out.println("输入您想删除的评论的ID：");
    }
    //点赞评论
    public static void likeComment() {
        System.out.println("输入您想点赞的评论的ID：");
    }
    //取消点赞
    public static void unlikeComment() {
        System.out.println("输入您想取消点赞的评论的ID：");
    }
    //添加文章到专栏
    public static void addBlogtoColumnView() {
        System.out.println("输入您想添加到专栏的名称：");
    }
    //更改文章倒专栏
    public void changeBlogtoColumnView() {
        printSeparator();
        System.out.println("该文章已有其对应的专栏，是否更改：");
        System.out.println("1.是");
        System.out.println("2.否");
        printSeparator();
    }
    //失败提示
    public static void error(){
        printSeparator();
        System.out.println("无效选项!");
    }
    public static void errorAddColumn(){
        printSeparator();
        System.out.println("无法找到此博文!");
    }
    public static void errorDeleteColumn(){
        printSeparator();
        System.out.println("此博文还未添加到此专栏中，移除失败!");
    }
    //成功提示
    public static void success(){
        System.out.println("操作成功!");
    }
    public static void errorComment(){
        printSeparator();
        System.out.println("您已被封禁，无法发表!");
    }
    public static void errorTitle(){
        printSeparator();
        System.out.println("请输入正确博文标题!");
    }
    public static void errordisfollow(){
        printSeparator();
        System.out.println("您还未关注过该博主!");
    }
    public static void errorfollower(){
        printSeparator();
        System.out.println("您已关注过该博主!");
    }
    public static void errorlike(){
        printSeparator();
        System.out.println("不能重复点赞!");
    }
    public static void errorunlike(){
        printSeparator();
        System.out.println("您未点过赞，无法取消点赞!");
    }
    public static void printSeparator() {
        System.out.println("-------------------------------------");
    }



}
