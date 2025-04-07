package com.linbinghui.csdn.util.session;

import com.linbinghui.csdn.dao.UserMapper;

import java.io.InputStream;
import java.util.List;

public interface SqlSession {
    //查询所有数据
    //statementId  sql语句的唯一id
    //parms 查询sql需要的参数，可变参数
    //返回结果集
<E>List<E> selectList(String statementId,Object... parms)throws Exception;

    //根据条件查询单个对象
    //返回对象
    <E> E selectOne(String statementId,Object... parms) throws Exception;
    <E> E insert(String statementId,Object... parms) throws Exception;
    <E> E update(String statementId,Object... parms) throws Exception;
    <E> E delete(String statementId,Object... parms) throws Exception;

    //返回usermapper接口的代理类对象
 <T> T getMapper(Class<T> mapperClass)throws Exception;


}
