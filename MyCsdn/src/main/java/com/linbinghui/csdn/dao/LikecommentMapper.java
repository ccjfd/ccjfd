package com.linbinghui.csdn.dao;

import com.linbinghui.csdn.entity.Likecomment;

import java.util.List;

public interface LikecommentMapper {
    List<Likecomment> selectBylikedcomment(Likecomment likecomment);
    Integer insert(Likecomment likecomment);
    Integer delete(Likecomment likecomment);
}
