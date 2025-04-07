package com.linbinghui.csdn.service;

import com.linbinghui.csdn.controller.CurrentUser;
import com.linbinghui.csdn.dao.BlogMapper;
import com.linbinghui.csdn.dao.FollowMapper;
import com.linbinghui.csdn.dao.LikeblogMapper;
import com.linbinghui.csdn.dao.UserMapper;
import com.linbinghui.csdn.entity.Blog;
import com.linbinghui.csdn.entity.Follow;
import com.linbinghui.csdn.entity.Likeblog;
import com.linbinghui.csdn.entity.User;
import com.linbinghui.csdn.view.PersonalCenterView;

import java.util.ArrayList;
import java.util.List;


public class PersonalCenterService {
    private static UserMapper userMapper = null;
    private static FollowMapper followMapper = null;
    private static LikeblogMapper likeMapper = null;
    private static BlogMapper blogMapper = null;

    public PersonalCenterService(UserMapper userMapper, FollowMapper followMapper, LikeblogMapper likeMapper, BlogMapper blogMapper) {
        this.userMapper = userMapper;
        this.followMapper = followMapper;
        this.likeMapper = likeMapper;
        this.blogMapper = blogMapper;
    }

    //个人信息
    public static void personalInformation(String username){
        User u= new User();
        u.setName(username);
        User selfInformation = userMapper.selectByName(u);
        System.out.println("用户昵称：" +selfInformation.getName());
        System.out.println("状态：" + selfInformation.getStatus());
    }

    //我关注过的博主
public List<User> followedBlogger(){
        Follow f = new Follow();
        f.setFollowerid(CurrentUser.getCurrentUser().getId());
    List<Follow> followedBlogger = followMapper.selectByUserId(f);
    if (followedBlogger.size() == 0){
        PersonalCenterView.errorfollow();
        return null;
    }
    List<User> userList = new ArrayList<User>();
    for (Follow follow : followedBlogger){
        User u=new User();
        u.setId(follow.getFollowedid());
        User user = userMapper.selectById(u);
        userList.add(user);
    }
    return userList;
}

    //我点赞过的博文
    public List<Blog> likedBlog(){
        Likeblog l = new Likeblog();
        l.setLikerid(CurrentUser.getCurrentUser().getId());
        List<Likeblog> likedBlog = likeMapper.selectByuserid(l);
        if (likedBlog.size() == 0){
            PersonalCenterView.errorlike();
            return null;
        }
        List<Blog> blogList = new ArrayList<Blog>();
        for (Likeblog likeblog : likedBlog){
            Blog b = new Blog();
            b.setId(likeblog.getLikedblogid());
            Blog blog = blogMapper.selectBlogById(b);
            blogList.add(blog);
        }
        return blogList;
    }
}
