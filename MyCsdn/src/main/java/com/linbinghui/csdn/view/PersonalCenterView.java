package com.linbinghui.csdn.view;

import com.linbinghui.csdn.entity.Blog;
import com.linbinghui.csdn.entity.User;

import java.util.List;
import java.util.Scanner;

public class PersonalCenterView {
    private static final Scanner scanner = new Scanner(System.in);
    public static void personalCenterMenu()
    {
        printSeparator();
        System.out.println("1. 个人信息");
        System.out.println("2. 我关注过的博主");
        System.out.println("3. 我点赞过的博文");
        System.out.println("4. 退出个人中心");
        printSeparator();
        System.out.print("请选择操作: ");
    }

    //我关注过的博主
    public static void followedBlogger(List<User> userlist)
    {
        if(userlist==null){
            return;
        }
        for(User user:userlist){
            System.out.println("博主ID:"+user.getId()+"博主名:"+user.getName());
        }
    }
//我点赞过的博文
    public static void likedBlog(List<Blog> bloglist)
    {
        if(bloglist==null){
            return;
        }
        for(Blog blog:bloglist){
            System.out.println("博文ID:"+blog.getId()+"博文标题:"+blog.getTitle());
        }
    }
    public static void errorfollow(){
        System.out.println("您还没有关注过博主");
    }
    public static void errorlike(){
        System.out.println("您还没有点赞过博文");
    }
    public static void error(){
        System.out.println("无效选项!");
    }
    private static void printSeparator() {
        System.out.println("-------------------------------------");
    }
}
