package com.linbinghui.csdn.controller;

import com.linbinghui.csdn.entity.Blog;
import com.linbinghui.csdn.service.SearchService;
import com.linbinghui.csdn.service.viewMyBlogService;
import com.linbinghui.csdn.view.SearchView;

import java.util.Scanner;

import static java.lang.System.exit;

public class SearchController {
    private static final Scanner scanner = new Scanner(System.in);
    private static SearchService searchService = null;
    private static SearchView searchView = null;
    private static viewMyBlogService viewmyblogservice = null;

    public SearchController(SearchService searchService, SearchView searchView, viewMyBlogService viewmyblogservice){
        this.searchView = searchView;
        this.searchService = searchService;
        this.viewmyblogservice = viewmyblogservice;
    }


    //搜索操作
    public static void searchOperater() {
        while (true){
            searchView.searchMenu();
            switch (scanner.nextInt()) {
                case 1://博主
                searchBloger();
                    break;
                case 2://文章
                searchBlog();
                    break;
                case 3://专栏
                searchColumn();
                    break;
                case 4://返回
                    return;

                default:
                    searchView.error();
            }
        }
    }

    //搜索博主
    public static void searchBloger() {
        SearchView.searchBloger();
        Blog blog = SearchView.showSearchBloger(searchService.searchBloger(scanner.next()));
        if (blog == null){
            return;
        }
        operAboutBlog(blog);
    }

//搜索文章
    public static void searchBlog() {
        SearchView.searchBlog();
        Blog blog = SearchView.showSearchBlog(searchService.searchBlog(scanner.next()));
        if (blog == null){return;}
        operAboutBlog(blog);
    }

//搜索专栏
    public static void searchColumn() {
        SearchView.searchColumn();
        Blog blog = SearchView.showSearchColumn(searchService.searchColumn(scanner.next()));
        if (blog == null){
            return;
        }
        operAboutBlog(blog);
    }
//对博文进行操作
    public static void operAboutBlog(Blog blog) {
        while (true){
            searchView.operAboutBlogView();
            switch (scanner.nextInt()) {
                case 1://点赞博文
                    searchService.likeBlog(blog.getId());
                    break;
                case 2://取消点赞
                    searchService.unlikeBlog(blog.getId());
                    break;
                case 3://关注博主
                    searchService.followBlogger(blog.getUserid());
                    break;
                case 4://取关
                    searchService.unfollowBlogger(blog.getUserid());
                    break;
                case 5://点赞评论
                    likeComment();
                    break;
                case 6://取消点赞
                    unlikeComment();
                    break;
                case 7://发表评论
                    viewMyBlogService.publishComment(scanner.next(), blog);
                    break;
                case 8://举报
                    searchService.report(blog.getId());
                    break;
                case 9://返回
                    return;

                default:
                    searchView.error();
                    break;
            }
        }
    }

    //点赞评论
    public static void likeComment() {
        searchView.likeComment();
        int commentid = scanner.nextInt();
        searchService.likeComment(commentid);
    }
    //取消点赞
    public static void unlikeComment() {
        searchView.unlikeComment();
        int commentid = scanner.nextInt();
        searchService.unlikeComment(commentid);
    }

}
