package com.linbinghui.csdn.dao;

import com.linbinghui.csdn.entity.Likeblog;

import java.util.List;

public interface LikeblogMapper {
    List<Likeblog> selectByblogid(Likeblog likeblog);
    List<Likeblog> selectByuserid(Likeblog likeblog);
    Integer insert(Likeblog likeblog);
    Integer delete(Likeblog likeblog);
}
