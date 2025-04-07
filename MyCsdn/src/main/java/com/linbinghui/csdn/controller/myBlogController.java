package com.linbinghui.csdn.controller;

import com.linbinghui.csdn.entity.Blog;
import com.linbinghui.csdn.service.myBlogService;
import com.linbinghui.csdn.view.LoginView;
import com.linbinghui.csdn.view.myBlogView;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class myBlogController {
    private static final Scanner scanner = new Scanner(System.in);

    private  myBlogService myblogservice=null;
    public myBlogController(myBlogService myblogservice){
        this.myblogservice = myblogservice;
    }

    static myBlogView myblogview = new myBlogView();
    public static void myBlogOperater(){
            while (true){
                myblogview.myBlogMenu();
                int choice = scanner.nextInt();
                switch (choice){
                    case 1://查看我的博文
                        viewMyBlogController.viewMyBlogOperater(viewmyBlog());
                        break;
                    case 2://发布博文
                        publishBlog();
                        break;
                    case 3://我的专栏
                        myColumnController.myColumnOperater();
                        break;
                    case 4://退出
                        return;
                    default:
                        myblogview.error();
                        break;
                }
            }
    }


//查看我发布过的博文（粗略的，只显示标题）
    private static List<Blog> viewmyBlog() {
        myblogview.printSeparator();
        List<Blog> blog = myBlogService.showMyBlog();
        List<Blog> myblog = myblogview.showMyBlogTitle(blog);
        myblogview.printSeparator();
        return myblog;
    }

    //发布博文
    public static void publishBlog(){
        if(CurrentUser.getCurrentUser().getStatus().equals("active")){
            String title = myblogview.putInTitle();
            String content = myblogview.putInContent();
            myBlogService.InsertBlog(title,content);
            myblogview.success();
        }else{
            myblogview.errorpublish();
        }

    }
}
