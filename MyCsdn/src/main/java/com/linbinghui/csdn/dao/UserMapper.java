package com.linbinghui.csdn.dao;

import com.linbinghui.csdn.entity.User;

import java.util.List;

public interface UserMapper {
    List<User> selectlist();
    List<User> selectBanUser();
    Integer insert(User user);
    User selectByName(User user);
    User selectById(User user);
    String updateUserStatus(User user);
}
