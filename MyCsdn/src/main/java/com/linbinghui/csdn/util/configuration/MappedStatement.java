package com.linbinghui.csdn.util.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*@Data
@AllArgsConstructor
@NoArgsConstructor*/
//封装usermapper.xml解析sql语句之后的信息
public class MappedStatement {
    //解析xml配置文件  SQL语句映射（usermapper)
private String id;//id标识 每条SQL的唯一标识
private String resultType;
private String parameterType;
private String sql;


    public MappedStatement() {
    }

    public MappedStatement(String id, String resultType, String parameterType, String sql) {
        this.id = id;
        this.resultType = resultType;
        this.parameterType = parameterType;
        this.sql = sql;
    }

    /**
     * 获取
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取
     * @return resultType
     */
    public String getResultType() {
        return resultType;
    }

    /**
     * 设置
     * @param resultType
     */
    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    /**
     * 获取
     * @return parameterType
     */
    public String getParameterType() {
        return parameterType;
    }

    /**
     * 设置
     * @param parameterType
     */
    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    /**
     * 获取
     * @return sql
     */
    public String getSql() {
        return sql;
    }

    /**
     * 设置
     * @param sql
     */
    public void setSql(String sql) {
        this.sql = sql;
    }

    public String toString() {
        return "MappedStatement{id = " + id + ", resultType = " + resultType + ", parameterType = " + parameterType + ", sql = " + sql + "}";
    }
}
