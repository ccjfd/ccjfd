package com.linbinghui.csdn.controller;

import com.linbinghui.csdn.entity.Blog;
import com.linbinghui.csdn.service.AdminService;
import com.linbinghui.csdn.view.AdminView;

import java.util.Scanner;

import static java.lang.System.exit;

public class AdminController {
    private static final Scanner scanner = new Scanner(System.in);
    private static AdminService adminService;
    private static AdminView adminView;
    public AdminController(AdminService adminService, AdminView adminView) {
        this.adminService = adminService;
        this.adminView = adminView;
    }
    //管理员操作
    public static void AdminOperater(boolean result) {
        if (result) {
            viewBlogorUser();
        }else {
            return;
        }
    }
    public static void viewBlogorUser() {
        while (true){
            adminView.viewBlogorUserMenu();
            switch (scanner.nextInt()) {
                case 1://查看用户
                    adminView.adminUserMenu();
                    break;
                case 2://查看博文
                    adminBlogMenu();
                    break;
                case 3://返回
                return;
                default:
                    adminView.error();
                    break;
            }
        }
    }

    //展示博文
    public static void adminBlogMenu() {
        Blog blog = adminView.adminBlogMenu();
        if (blog==null){
            return;
        }
        AdminOperater(blog);
    }
    public static void AdminOperater(Blog blog) {
        while (true){
            adminView.adminOperaterMenu();
            switch (scanner.nextInt()) {
                case 1://删除博文
                adminService.deleteBlog(blog);
                    break;
                case 2://删除评论
                deleteComment(blog);
                    break;
                case 3://封禁用户
                    if (adminService.banUser(blog.getUserid())){
                        adminView.success();
                    }else{
                        adminView.errorban();
                    }
                    break;
                case 4://返回
                return;

                default:
                    adminView.error();
                    break;
            }
        }
    }
    //删除评论
    public static void deleteComment(Blog blog) {
        adminView.choosedeletecommentid();
        adminService.deleteComment(scanner.nextInt());
        adminView.success();
    }

}
