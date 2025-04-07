package com.linbinghui.csdn.service;

import com.linbinghui.csdn.controller.CurrentUser;
import com.linbinghui.csdn.dao.AdminMapper;
import com.linbinghui.csdn.dao.UserMapper;
import com.linbinghui.csdn.entity.Admin;
import com.linbinghui.csdn.entity.User;
import com.linbinghui.csdn.util.MD5util;
import com.linbinghui.csdn.view.ConsoleView;

import java.security.MessageDigest;


public class AuthService {
    private final UserMapper userMapper;
    private final AdminMapper adminMapper;
    private static final String STATIC_SALT = "fixed_system_salt";

    public AuthService(UserMapper userMapper, AdminMapper adminMapper) {
        this.userMapper = userMapper;
        this.adminMapper = adminMapper;
    }

    ConsoleView consoleView = new ConsoleView();
    //用户注册
    public boolean register(String username, String password) {
        User u= new User();
        u.setName(username);
        User existingUser = userMapper.selectByName(u);
        if (existingUser != null) {
            consoleView.errorregister();
            return false;
        }

        User user = new User();
        user.setName(username);
        user.setPassword(MD5util.md5(password + STATIC_SALT));
        user.setStatus("active");
        userMapper.insert(user);
        return true;
    }
//管理员注册
    public boolean adminRegister(String username, String password) {
        Admin admin= new Admin();
        admin.setAdminname(username);
        Admin existingAdmin = adminMapper.selectByName(admin);
        if (existingAdmin != null) {
            consoleView.errorregister();
            return false;
        }
        Admin ad = new Admin();
        ad.setAdminname(username);
        ad.setAdminpassword(MD5util.md5(password + STATIC_SALT));
        adminMapper.insert(ad);
        return true;
    }
    //管理员登陆
    public boolean adminLogin(String username, String password) {
        Admin admin= new Admin();
        admin.setAdminname(username);
        admin.setAdminpassword(MD5util.md5(password + STATIC_SALT));
        Admin loginadmin = adminMapper.selectByName(admin);
        if (loginadmin == null || !loginadmin.getAdminpassword().equals(MD5util.md5(password + STATIC_SALT))) {
            return false;//账号密码错误
        }
        return true;
    }
    // 登录
    public boolean login(String username, String password) {
        User U= new User();
        U.setName(username);
        U.setPassword(MD5util.md5(password + STATIC_SALT));
        User loginuser = userMapper.selectByName(U);

        if (loginuser == null || !loginuser.getPassword().equals(MD5util.md5(password + STATIC_SALT))) {
            return false;//账号密码错误
        }
        /*if ("disabled".equals(user.getStatus())) {
            return -1;//账户已被禁用
        }*/
        CurrentUser.setCurrentUser(loginuser);
        return true;//登录成功
    }
}