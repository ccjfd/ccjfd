package com.linbinghui.csdn.util.executor;



import com.linbinghui.csdn.entity.User;
import com.linbinghui.csdn.util.configuration.BoundSql;
import com.linbinghui.csdn.util.configuration.CommandType;
import com.linbinghui.csdn.util.configuration.Configuration;
import com.linbinghui.csdn.util.configuration.MappedStatement;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.*;

//SQL语句执行器(最终执行了JDBC的方法）
public class SimpleExecutor implements Executor{
    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... parms) throws Exception {
        //获取数据库连接
        Connection connection = configuration.getDataSource().getConnection();
        //获取要执行的SQL语句
        //解析SQL语句，把 SQL语句中的#{id}替换成 ？
        //因为 JDBC是不是别 #{id}占位符的
        String sql=mappedStatement.getSql();
        //SQL语句转换
        BoundSql boundSql=this.getBoundSql(sql);
        //获取
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());
        //设置参数，获取参数user类
        String parameterType=mappedStatement.getParameterType();
        Class<?> parameterTypeClass = this.getClassType(parameterType);
        //获取SQL语句的参数集合
        List<String> parameterMappingList=boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            //获取参数名称
            String content = parameterMappingList.get(i);
            //暴力反射
            Field declaredField = parameterTypeClass.getDeclaredField(content);
            declaredField.setAccessible(true);
            //取出参数的值
            Object data = declaredField.get(parms[0]);

            preparedStatement.setObject(i+1,data);
        }
// 执行SQL语句

        String id=mappedStatement.getId();
        ResultSet resultSet = null;
        //判断是增删改查
        if (id.contains(CommandType.DELETE.toString().toLowerCase())||
                id.contains(CommandType.UPDATE.toString().toLowerCase())||
                id.contains(CommandType.INSERT.toString().toLowerCase())){

            Integer result = preparedStatement.executeUpdate();
            List<Integer> resultList=new ArrayList<Integer>();
            resultList.add(result);
            return (List<E>) resultList;
        }else{
            //查询
            resultSet = preparedStatement.executeQuery();

        }
        //获取返回值的类型
        String resultType=mappedStatement.getResultType();//com.bruce.pojo.User
        Class<?> resultTypeClass = this.getClassType(resultType);
        List<Object> objects = new ArrayList<Object>();
        //查询结果集封装
        while (resultSet.next()){
            //调用无参构造方法生成对象
            Object o = resultTypeClass.newInstance();
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                //获取字段名
                String columnName = metaData.getColumnName(i);
                //值
                Object value = resultSet.getObject(columnName);
                //属性封装
                //使用反射根据数据库表和实体类的属性和字段对应关系数据封装
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o, value);
            }
            objects.add(o);
        }

        return (List<E>)objects;
    }



    //根据类的全名称，获取参数的类class
    public Class<?> getClassType(String parameterType) throws ClassNotFoundException {

        if (parameterType!=null){
            Class<?> clazz =Class.forName(parameterType);
            return clazz;
        }
        return null;
    }


Map<Integer,Integer> map=new TreeMap<Integer,Integer>();
int findPosition=0;
List<String> parameterMappings=new ArrayList<String>();

private BoundSql getBoundSql(String sql){
    parseSql(sql);
    Set<Map.Entry<Integer,Integer>> entries=map.entrySet();
    for (Map.Entry<Integer,Integer> entry:entries){
        Integer key=entry.getKey()+2;
        Integer value=entry.getValue();
        String parameter=sql.substring(key,value);
        parameterMappings.add(parameter);
    }
    for (String s:parameterMappings){
        sql=sql.replace("#{"+s+"}","?");
    }
    BoundSql boundSql=new BoundSql(sql,parameterMappings);
    return boundSql;
}

    private void parseSql(String sql) {
    int openIndex=sql.indexOf("#{",findPosition);
    if (openIndex!=-1){
        int endIndex=sql.indexOf("}",findPosition+1);
        if (endIndex!=-1){
            map.put(openIndex,endIndex);
            findPosition=endIndex+1;
            parseSql(sql);
        }else{
            System.out.println("SQL语句解析错误");
        }
    }
    }


}
