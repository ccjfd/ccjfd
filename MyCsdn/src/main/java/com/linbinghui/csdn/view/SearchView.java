package com.linbinghui.csdn.view;

import com.linbinghui.csdn.controller.SearchController;
import com.linbinghui.csdn.entity.Blog;
import com.linbinghui.csdn.entity.Column;
import com.linbinghui.csdn.entity.Comment;
import com.linbinghui.csdn.entity.User;
import com.linbinghui.csdn.service.SearchService;

import java.util.List;
import java.util.Scanner;

import static com.linbinghui.csdn.service.viewMyBlogService.getCommentUserName;


public class SearchView {
    private static final Scanner scanner = new Scanner(System.in);
    static SearchService searchService;
    public SearchView(SearchService searchService) {
        this.searchService = searchService;
    }


    //菜单
    public static void searchMenu()
    {
        printSeparator();
        System.out.println("请选择搜索方式:");
        System.out.println("1.搜索博主");
        System.out.println("2.搜索博文");
        System.out.println("3.搜索专栏");
        System.out.println("4.返回");
        printSeparator();
        System.out.print("请选择操作: ");
    }
//搜索博主
    public static void searchBloger(){
        System.out.println("请输入要搜索的博主名称：");
    }


    public static Blog showSearchBloger(List<User> userlist){
        if(userlist==null){return null;}
        printSeparator();
        for (User user : userlist) {
            System.out.println("博主ID：" + user.getId() + " 昵称：" + user.getName() + " 状态：" + user.getStatus());
        }
        printSeparator();
        System.out.println("选择博主ID进行查看");
        List<Blog> bloglist = searchService.selectBlogByuserid(scanner.nextInt());
        if (bloglist.get(0) != null){
            for (Blog blog : bloglist) {
                System.out.println("博文ID：" + blog.getId());
                System.out.println("博主：" + blog.getUserid() + " 专栏：" + blog.getColumnid() + " 日期：" + blog.getBlogdate());
                System.out.println("标题：" + blog.getTitle() + " 点赞量：" + blog.getLikenum()  + " 举报数：" + blog.getAgainstnum());
            }
            printSeparator();
            System.out.println("1.选择博文ID进行查看");
            System.out.println("2.返回");
            int choice = scanner.nextInt();
            if (choice == 2){
                return null;
            }
            Blog blog = searchService.searchBlogContent(scanner.nextInt());
            printSeparator();
            System.out.println("标题：" + blog.getTitle());
            System.out.println("作者ID：" + blog.getUserid());
            System.out.println("内容：" + blog.getContent());
            printSeparator();
            System.out.println("评论区：");
            List<Comment> commentlist = searchService.getMyBlogComment(blog);
            if (commentlist != null){
                ShowComment(commentlist);
            }else if (commentlist.size() == 0){
                System.out.println("该博文暂无评论");
                printSeparator();
            }
            return blog;

        }else{
            System.out.println("该博主暂无文章");
            printSeparator();
            return null;
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

//搜索博文
public static void searchBlog(){
    System.out.println("请输入要搜索的博文标题：");
}
//展示搜索到的博文
    public static Blog showSearchBlog(List<Blog> bloglist){
        if(bloglist!=null){
            printSeparator();
            for (Blog blog : bloglist) {
                System.out.println("博文ID：" + blog.getId());
                System.out.println("博主：" + blog.getUserid() + " 专栏：" + blog.getColumnid() + " 日期：" + blog.getBlogdate());
                System.out.println("标题：" + blog.getTitle() + " 点赞量：" + blog.getLikenum()  + " 举报数：" + blog.getAgainstnum());
            }
            printSeparator();
            System.out.println("搜索结果共" + bloglist.size() + "条");
            System.out.println("选择博文ID进行查看");
            Blog blog = searchService.searchBlogContent(scanner.nextInt());
            printSeparator();
            System.out.println("标题：" + blog.getTitle());
            System.out.println("作者ID：" + blog.getUserid());
            System.out.println("内容：" + blog.getContent());
            printSeparator();
            System.out.println("评论区：");
            List<Comment> commentlist = searchService.getMyBlogComment(blog);
            if (commentlist != null){
                ShowComment(commentlist);
            }else if (commentlist.size() == 0){
                System.out.println("该博文暂无评论");
                printSeparator();
            }
            return blog;
        }
        System.out.println("该博主还未发表过博文");
        return null;
    }

//搜索专栏
    public static void searchColumn(){
        System.out.println("请输入要搜索的专栏名称：");
    }
    public static Blog showSearchColumn(List<Column> columnlist){
        if(columnlist==null){return null;}
        printSeparator();
        for (Column column : columnlist) {
            System.out.println("专栏博主：" + column.getUserid());
            System.out.println("专栏ID：" + column.getId() + " 专栏名称：" + column.getColumnname());
        }
        printSeparator();
        System.out.println("选择专栏ID进行查看");
        List<Blog> bloglist = searchService.selectBlogByColumnId(scanner.nextInt());
        if (bloglist == null){
            System.out.println("该专栏暂无文章");
            printSeparator();
            return null;
        }
        for (Blog blog : bloglist) {
            System.out.println("博文ID：" + blog.getId());
            System.out.println("博主：" + blog.getUserid() + " 专栏：" + blog.getColumnid() + " 日期：" + blog.getBlogdate());
            System.out.println("标题：" + blog.getTitle() + " 点赞量：" + blog.getLikenum()  + " 举报数：" + blog.getAgainstnum());
        }
        printSeparator();
        System.out.println("是否选择博文置顶：1.是    2.否");
        if (scanner.nextInt() == 1){
            System.out.println("请选择置顶文章ID：");
            searchService.topBlog(scanner.nextInt());
            success();
        }
        printSeparator();
        System.out.println("1.选择博文ID进行查看");
        System.out.println("2.返回");
        int choice = scanner.nextInt();
        if (choice == 2){
            return null;
        }
        Blog blog = searchService.searchBlogContent(scanner.nextInt());
        printSeparator();
        System.out.println("标题：" + blog.getTitle());
        System.out.println("作者ID：" + blog.getUserid());
        System.out.println("内容：" + blog.getContent());
        printSeparator();
        System.out.println("评论区：");
        List<Comment> commentlist = searchService.getMyBlogComment(blog);
        if (commentlist != null){
            ShowComment(commentlist);
        }else if (commentlist.size() == 0){
            System.out.println("该博文暂无评论");
            printSeparator();
        }

        return blog;
    }

//博文操作菜单
    public static void operAboutBlogView() {
        printSeparator();
        System.out.println("1.点赞博文  2.取消点赞博文");
        System.out.println("3.关注博主  4.取关博主");
        System.out.println("5.点赞评论  6.取消点赞评论");
        System.out.println("7.发表评论  8.举报");
        System.out.println("9.返回");
        printSeparator();
        System.out.println("请选择操作:");
    }


    //点赞评论
    public static void likeComment() {
        System.out.println("输入您想点赞的评论的ID：");
    }
    //取消点赞
    public static void unlikeComment() {
        System.out.println("输入您想取消点赞的评论的ID：");
    }

    //错误提示
    public static void error(){
        System.out.println("无效选项!");
    }
    public static void findblogerror(){
        System.out.println("您搜索的博文不存在!");
    }
    public static void findblogererror(){
        System.out.println("您搜索的博主不存在!");
    }
    public static void findcolumnerror(){
        System.out.println("您搜索的专栏不存在!");
    }
    //成功提示
    public static void success(){
        System.out.println("操作成功!");
    }
    public static void printSeparator() {
        System.out.println("-------------------------------------");
    }
}
