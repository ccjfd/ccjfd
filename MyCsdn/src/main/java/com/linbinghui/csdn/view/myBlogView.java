package com.linbinghui.csdn.view;

import com.linbinghui.csdn.entity.Blog;

import java.util.List;
import java.util.Scanner;

public class myBlogView {
    private static final Scanner scanner = new Scanner(System.in);
    public static void myBlogMenu() {
        printSeparator();
        System.out.println("1. 查看我的博文");
        System.out.println("2. 发布博文");
        System.out.println("3. 我的专栏");
        System.out.println("4. 退出我的博文");
        printSeparator();
        System.out.print("请选择操作: ");
    }

    //发布博文
    //输入标题和内容
    public static String putInTitle(){
        System.out.println("请输入标题:");
        String title = scanner.nextLine();
        //判断输入是否为空
        while (title.isEmpty()){
            System.out.println("标题不能为空!");
            title = scanner.nextLine();
        }
        return title;
    }
    public static String putInContent(){
        System.out.println("请输入内容:");
        String content = scanner.nextLine();
        while (content.isEmpty()){
            System.out.println("内容不能为空!");
            content = scanner.nextLine();
        }
        return content;
    }

    //展示我的博文
    public static List<Blog> showMyBlogTitle(List<Blog> blog){
        if (blog != null){
            for(int i = 1; i <= blog.size(); i++){
                System.out.println(i + ". " + blog.get(i-1).getTitle()
                        + " 点赞量：" + blog.get(i-1).getLikenum()
                        + " 评论量：" + blog.get(i-1).getCommentnum()
                        + " 举报数：" + blog.get(i-1).getAgainstnum());
            }
            return blog;
        }else{
            System.out.println("您还没有发布过博文!");
            return null;
        }
    }
    //错误提示
    public static void error(){
        System.out.println("无效选项!");
    }
    public static void errorpublish(){
        System.out.println("您已被封禁，无法发布!");
    }
    //成功提示
    public static void success(){
        System.out.println("操作成功!");
    }
    public static void printSeparator() {
        System.out.println("-------------------------------------");
    }
}
