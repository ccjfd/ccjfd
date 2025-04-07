package com.linbinghui.csdn.dao;

import com.linbinghui.csdn.entity.Follow;

import java.util.List;

public interface FollowMapper {
    List<Follow> selectByUserId(Follow follow);
Integer insert(Follow follow);
Integer delete(Follow follow);
}
