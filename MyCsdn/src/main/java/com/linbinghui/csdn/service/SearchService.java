package com.linbinghui.csdn.service;

import com.linbinghui.csdn.dao.*;
import com.linbinghui.csdn.entity.*;
import com.linbinghui.csdn.view.SearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.linbinghui.csdn.controller.CurrentUser.getCurrentUser;
import static com.linbinghui.csdn.service.viewMyBlogService.viewmyblogview;

public class SearchService {

    private static UserMapper userMapper=null;
    private static BlogMapper blogMapper=null;
    private ColumnMapper columnMapper=null;
    private static LikecommentMapper likecommentMapper=null;
    private static CommentMapper commentMapper=null;
    private static FollowMapper followMapper=null;
    private static LikeblogMapper likeblogMapper=null;

    public SearchService( UserMapper userMapper ,ColumnMapper columnMapper, BlogMapper blogMapper, LikecommentMapper likecommentMapper, CommentMapper commentMapper, FollowMapper followMapper, LikeblogMapper likeblogMapper)
    {
        this.userMapper = userMapper;
        this.columnMapper = columnMapper;
        this.blogMapper = blogMapper;
        this.likecommentMapper = likecommentMapper;
        this.commentMapper = commentMapper;
        this.followMapper = followMapper;
        this.likeblogMapper = likeblogMapper;
    }

    //搜索博主
    public List<User> searchBloger(String keywords) {
        List<User> userlist = userMapper.selectlist();
        List<User> SearchUserlist = new ArrayList<>();
        for (User user : userlist) {
            if (user.getName().contains(keywords)) {
                SearchUserlist.add(user);
            }
        }
        if (SearchUserlist == null){
            SearchView.findblogererror();
            return null;
        }
        return SearchUserlist;
    }
public static List<Blog> selectBlogByuserid(int userid) {
        Blog b = new Blog();
        b.setUserid(userid);
        List<Blog> thisuser = blogMapper.selectBlogByUId(b);
        return thisuser;
    }

    public static List<Blog> selectBlogByColumnId(int columnid) {
        Blog b = new Blog();
        b.setColumnid(columnid);
        List<Blog> thiscolumn = blogMapper.selectBlogByColumnId(b);
        return thiscolumn;
    }

    //搜索博文
    public List<Blog> searchBlog(String keywords) {
        List<Blog> selectlist = blogMapper.selectlist();
        List<Blog> bloglist = new ArrayList<>();
        for (Blog blog : selectlist) {
            if (blog.getTitle().contains(keywords)){
                bloglist.add(blog);
            }
        }
        if (bloglist == null){
            SearchView.findblogerror();
            return null;
        }
        return bloglist;
    }

//搜索专栏
public List<Column> searchColumn(String keywords) {
        List<Column> selectlist = columnMapper.selectlist();
        List<Column> columnlist = new ArrayList<>();
        for (Column column : selectlist) {
            if (column.getColumnname().contains(keywords)){
                columnlist.add(column);
            }
        }
        if (columnlist == null){
            SearchView.findcolumnerror();
            return null;
        }
        return columnlist;
    }
    //置顶文章
    public static void topBlog(int blogid) {
        Blog blog = new Blog();
        blog.setId(blogid);
        //把1转化为int类型
        blog.setOrder(1);
        blogMapper.updateBlogOrder(blog);
    }
//查看博文内容
    public Blog searchBlogContent(int blogid) {
        Blog b = new Blog();
        b.setId(blogid);
        Blog blog = blogMapper.selectBlogById(b);
        return blog;
    }
    //获取评论区下的评论
    public List<Comment> getMyBlogComment(Blog blog) {
        Comment comment = new Comment();
        comment.setBlogid(blog.getId());
        List<Comment> commentList = commentMapper.selectCommentByBlogId(comment);
        return commentList;
    }

    //关注博主
    public static void followBlogger(int userid) {
        Follow f = new Follow();
        f.setFollowerid(getCurrentUser().getId());
        List<Follow> follows = followMapper.selectByUserId(f);
        if (follows.size() > 0) {
            for (Follow follow : follows) {
                if (follow.getFollowedid() == userid) {
                    viewmyblogview.errorfollower();
                    return;
                }
            }
        }
        Follow follow = new Follow();
        follow.setFollowerid(getCurrentUser().getId());
        follow.setFollowedid(userid);
        followMapper.insert(follow);
        viewmyblogview.success();
    }
    //取消关注博主
    public static void unfollowBlogger(int userid) {
        Follow f = new Follow();
        f.setFollowerid(getCurrentUser().getId());
        List<Follow> follows = followMapper.selectByUserId(f);
        if (follows.size() > 0) {
            for (Follow follow : follows) {
                if (follow.getFollowedid() == userid) {
                    followMapper.delete(follow);
                    viewmyblogview.success();
                    return;
                }
            }
        }
        viewmyblogview.errordisfollow();
    }
    //点赞博文
    public static void likeBlog(int blogid) {
        Likeblog Lb = new Likeblog();
        Lb.setLikerid(blogid);
        List<Likeblog> likers = likeblogMapper.selectByblogid(Lb);
        if (likers.size() > 0) {
            for (Likeblog likeblog : likers) {
                if (likeblog.getLikerid() == getCurrentUser().getId()) {
                    viewmyblogview.errorlike();
                    return;
                }
            }
        }
        Likeblog likeblog = new Likeblog();
        likeblog.setLikedblogid(blogid);
        likeblog.setLikerid(getCurrentUser().getId());
        likeblogMapper.insert(likeblog);
        Blog blog = new Blog();
        blog.setId(blogid);
        blog.setLikenum(blogMapper.selectBlogById(blog).getLikenum() + 1);
        blogMapper.updateBlogLike(blog);
        viewmyblogview.success();
    }
    //取消点赞博文
    public static void unlikeBlog(int blogid) {
        Likeblog Lb = new Likeblog();
        Lb.setLikerid(blogid);
        List<Likeblog> likers = likeblogMapper.selectByblogid(Lb);
        if (likers.size() > 0) {
            for (Likeblog likeblog : likers) {
                if (likeblog.getLikerid() == getCurrentUser().getId()) {
                    likeblogMapper.delete(likeblog);
                    Blog blog = new Blog();
                    blog.setId(blogid);
                    blog.setLikenum(blogMapper.selectBlogById(blog).getLikenum() - 1);
                    blogMapper.updateBlogLike(blog);
                    viewmyblogview.success();
                    return;
                }
            }
        }
        viewmyblogview.errorunlike();
    }
    //点赞评论
    public static void likeComment(int commentid) {
        Likecomment Lc = new Likecomment();
        Lc.setLikedcommentid(commentid);
        List<Likecomment> likecomments = likecommentMapper.selectBylikedcomment(Lc);
        if (likecomments.size() > 0) {
            for (Likecomment likecomment : likecomments) {
                if (likecomment.getLikerid() == getCurrentUser().getId()) {
                    viewmyblogview.errorlike();
                    return;
                }
            }
        }
        Likecomment likecomment = new Likecomment();
        likecomment.setLikedcommentid(commentid);
        likecomment.setLikerid(getCurrentUser().getId());
        likecommentMapper.insert(likecomment);
        Comment comment = new Comment();
        comment.setId(commentid);
        Comment thiscomment = commentMapper.selectBycommentid(comment);
        thiscomment.setLikenum(thiscomment.getLikenum() + 1);
        commentMapper.updateLikenum(thiscomment);
        viewmyblogview.success();
    }
    //取消点赞评论
    public static void unlikeComment(int commentid) {
        Likecomment Lc = new Likecomment();
        Lc.setLikedcommentid(commentid);
        List<Likecomment> likecomments = likecommentMapper.selectBylikedcomment(Lc);
        if (likecomments.size() > 0) {
            for (Likecomment likecomment : likecomments) {
                if (likecomment.getLikerid() == getCurrentUser().getId()) {
                    likecommentMapper.delete(likecomment);
                    Comment comment = new Comment();
                    comment.setId(commentid);
                    Comment thiscomment = commentMapper.selectBycommentid(comment);
                    thiscomment.setLikenum(thiscomment.getLikenum() - 1);
                    commentMapper.updateLikenum(thiscomment);
                    viewmyblogview.success();
                    return;
                }
            }
        }
        viewmyblogview.errorunlike();
    }
    //举报
    public static void report(int blogid) {
        Blog b = new Blog();
        b.setId(blogid);
        Blog blog = blogMapper.selectBlogById(b);
        blog.setAgainstnum(blog.getAgainstnum() + 1);
        blogMapper.updateBlogReport(blog);
        viewmyblogview.success();
    }

}
