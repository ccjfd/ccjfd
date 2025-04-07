package com.linbinghui.csdn.util.session;

import com.linbinghui.csdn.util.configuration.CommandType;
import com.linbinghui.csdn.util.configuration.Configuration;
import com.linbinghui.csdn.util.configuration.MappedStatement;
import com.linbinghui.csdn.util.executor.SimpleExecutor;

import java.lang.reflect.*;
import java.util.List;

public class DefaultSqlSession implements SqlSession{

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }



    @Override
    public <E> List<E> selectList(String statementId, Object... parms) throws Exception {
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = this.configuration.getMappedStatementMap().get(statementId);
        List<Object> list = simpleExecutor.query(configuration, mappedStatement, parms);
        return (List<E>) list;
    }

    @Override
    public <E> E selectOne(String statementId, Object... parms) throws Exception {
        List<Object> objects = this.selectList(statementId, parms);
        if (objects.size() == 1){
            return (E) objects.get(0);
        }else if (objects.size()>1){
            throw new RuntimeException("查询结果不唯一");
        }else{
            return null;
        }
    }

    @Override
    public <E> E insert(String statementId, Object... parms) throws Exception {
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = this.configuration.getMappedStatementMap().get(statementId);
        List<Object> list = simpleExecutor.query(configuration, mappedStatement, parms);
        if (list.size() > 0){
            return (E) list.get(0);
        }else{
            return (E) "0";
        }
    }

    @Override
    public <E> E update(String statementId, Object... parms) throws Exception {
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = this.configuration.getMappedStatementMap().get(statementId);
        List<Object> list = simpleExecutor.query(configuration, mappedStatement, parms);
        if (list.size() > 1){
            return (E) list.get(0);
        }else{
            return (E) "0";
        }
    }

    @Override
    public <E> E delete(String statementId, Object... parms) throws Exception {
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = this.configuration.getMappedStatementMap().get(statementId);
        List<Object> list = simpleExecutor.query(configuration, mappedStatement, parms);
        if (list.size() > 1){
            return (E) list.get(0);
        }else{
            return (E) "0";
        }
    }
//使用JDK动态代理给usermapper接口生成代理对象
    @Override
    public <T> T getMapper(Class<T> mapperClass) throws Exception {
        //使用JDK动态代理技术为mapper接口层(usermapper)生成动态代理对象，也就是实现类 并返回
        Object instance = Proxy.newProxyInstance(mapperClass.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //mapper 接口中的方法名字
                String methodName = method.getName();
                //接口中的全类名
                String className = method.getDeclaringClass().getName();
                //拼接SQL的唯一标识
                String StatmentId = className + "." + methodName;
                //获取方法被调用之后返回值的类型
                Type genericReturnType = method.getGenericReturnType();
                if (methodName.contains(CommandType.INSERT.toString().toLowerCase())) {
                    return insert(StatmentId, args);
                } else if (methodName.contains(CommandType.UPDATE.toString().toLowerCase())) {
                    return update(StatmentId, args);
                } else if (methodName.contains(CommandType.DELETE.toString().toLowerCase())) {
                    return delete(StatmentId, args);
                }
                //判断是否有不是以上几种的返回值类型（就是判断是不是泛型）
                if (genericReturnType instanceof ParameterizedType) {
                    return selectList(StatmentId, args);
                    //返回值是object类型的一个List
                } else {
                    //不是泛型则表示只查询一个对象
                    return selectOne(StatmentId, args);
                }
            }
        });
        return (T) instance;
    }
}
