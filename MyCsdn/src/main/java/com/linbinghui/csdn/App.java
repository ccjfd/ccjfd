package com.linbinghui.csdn;

import com.linbinghui.csdn.controller.*;
import com.linbinghui.csdn.dao.*;
import com.linbinghui.csdn.service.*;
import com.linbinghui.csdn.util.io.Resources;
import com.linbinghui.csdn.util.session.SqlSession;
import com.linbinghui.csdn.util.session.SqlSessionFactory;
import com.linbinghui.csdn.util.session.SqlSessionFactoryBuilder;
import com.linbinghui.csdn.view.AdminView;
import com.linbinghui.csdn.view.ConsoleView;
import com.linbinghui.csdn.view.SearchView;

import java.io.InputStream;




public class App
{
    public static void main( String[] args ) throws Exception {
        // 初始化MyBatis
        InputStream configStream = Resources.GetResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
        ColumnMapper columnMapper = sqlSession.getMapper(ColumnMapper.class);
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        LikecommentMapper likecommentMapper = sqlSession.getMapper(LikecommentMapper.class);
        FollowMapper followMapper = sqlSession.getMapper(FollowMapper.class);
        LikeblogMapper likeblogMapper = sqlSession.getMapper(LikeblogMapper.class);

        AuthService userService = new AuthService(userMapper, adminMapper);
        AuthController userController = new AuthController(userService);

        LoginController loginController = new LoginController();

        AdminService adminService = new AdminService(adminMapper, blogMapper, commentMapper, userMapper);
        AdminView adminView = new AdminView(adminService);
        AdminController adminController = new AdminController(adminService, adminView);

        myBlogService myblogService= new myBlogService(blogMapper);
        myBlogController myblogController = new myBlogController(myblogService);

        PersonalCenterService personalCenterService = new PersonalCenterService(userMapper,followMapper, likeblogMapper,blogMapper);
        PersonalCenterController personalCenterController = new PersonalCenterController(personalCenterService);

        viewMyBlogService viewmyblogService = new viewMyBlogService(commentMapper, userMapper, blogMapper, likecommentMapper, columnMapper);
        viewMyBlogController viewmyblogController = new viewMyBlogController(viewmyblogService);

        myColumnService mycolumnService = new myColumnService(columnMapper);
        myColumnController mycolumnController = new myColumnController(mycolumnService);

        SearchService searchService = new SearchService(userMapper, columnMapper, blogMapper, likecommentMapper, commentMapper, followMapper, likeblogMapper);
        SearchController searchController = new SearchController(searchService, new SearchView(searchService), viewmyblogService);

        while (true) {
            AuthController.AuthOperater();
        }

    }
}
