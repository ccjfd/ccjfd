package com.linbinghui.csdn.view;


import com.linbinghui.csdn.controller.AdminController;
import com.linbinghui.csdn.entity.Blog;
import com.linbinghui.csdn.entity.Comment;
import com.linbinghui.csdn.entity.User;
import com.linbinghui.csdn.service.AdminService;

import java.util.List;
import java.util.Scanner;

import static com.linbinghui.csdn.service.viewMyBlogService.getCommentUserName;


public class AdminView {
    private static final Scanner scanner = new Scanner(System.in);
    static AdminService adminService;
    public AdminView(AdminService adminService)
    {
        this.adminService = adminService;
    }

    //展示用户
    public static void adminUserMenu()
    {
        printSeparator();
        System.out.println("用户列表：");
        List<User> userlist = adminService.getbanUser();
        if (userlist.size() == 0){
            System.out.println("暂无被封禁的用户！");
            printSeparator();
            return;
        }
        for (User user : userlist) {
            System.out.println("用户ID：" + user.getId() + " 昵称：" + user.getName() + " 状态：" + user.getStatus());
        }
        printSeparator();
        System.out.println("是否选择解封用户：1.是  2.返回");
        if (scanner.nextInt() == 1){
            System.out.println("输入要解封的用户ID：");
            if(adminService.nobanUser(scanner.nextInt()) == true){
                success();
            }else {
                errornoban();
            }
        }
    }

    //展示博文
    public static Blog adminBlogMenu()
    {
        printSeparator();
        System.out.println("用户发表的博文(按举报数排序）：");
        List<Blog> allBlog = adminService.getAllBlog();
        if (allBlog.size() == 0){
            nullBlogerror();
            return null;
        }
        for (Blog blog : allBlog) {
            System.out.print("博文ID：" + blog.getId() + "  ");
            System.out.print("博主ID：" + blog.getUserid() + "  ");
            System.out.println(blog.getTitle() + "  " + blog.getAgainstnum());
        }
        printSeparator();
        System.out.println("选择博文ID进行查看");
        Blog blog = adminService.selectBlogById(scanner.nextInt());
        printSeparator();
        System.out.println("标题：" + blog.getTitle());
        System.out.println("作者ID：" + blog.getUserid());
        System.out.println("内容：" + blog.getContent());
        printSeparator();
        System.out.println("评论区：");
        List<Comment> commentlist = adminService.getMyBlogComment(blog);
        if (commentlist != null){
            ShowComment(commentlist);
        }else if (commentlist.size() == 0){
            System.out.println("该博文暂无评论");
            printSeparator();
        }
        return blog;
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

    //管理员菜单
    public static void viewBlogorUserMenu()
    {
        printSeparator();
        System.out.println("1.查看用户");
        System.out.println("2.查看博文");
        System.out.println("3.返回");
        printSeparator();
    }
//管理员对博文进行操作
    public static void adminOperaterMenu()
    {
        System.out.println("1.删除博文");
        System.out.println("2.删除评论");
        System.out.println("3.封禁用户");
        System.out.println("4.返回");
    }
//选择删除的评论
    public static void choosedeletecommentid(){
        System.out.println("输入您想删除的评论ID：");
    }
    public static void success(){
        System.out.println("操作成功!");
    }

    public static void nullBlogerror(){
        System.out.println("还未有用户发表过文章！");
    }
    public static void errorban(){
        System.out.println("该用户已处于封禁状态！");
    }
    public static void errornoban(){
        System.out.println("该用户还未被封禁！");
    }
    public static void error(){
        System.out.println("无效选项!");
    }
    private static void printSeparator() {
        System.out.println("-------------------------------------");
    }
}
