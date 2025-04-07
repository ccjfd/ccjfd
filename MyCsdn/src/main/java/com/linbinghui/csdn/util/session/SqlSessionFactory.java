package com.linbinghui.csdn.util.session;

public interface SqlSessionFactory {
    //作用：获取sqlSession对象
SqlSession openSession();
}
