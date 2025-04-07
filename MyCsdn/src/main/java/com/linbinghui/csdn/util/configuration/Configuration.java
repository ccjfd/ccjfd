package com.linbinghui.csdn.util.configuration;


import com.linbinghui.csdn.util.connectionpool.DataSourceConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

///对mybatis框架的sql配置的封装，封装两个配置文件1.核心的配置文件（mybatisconfig.xml）2.sql配置文件（usermapper.xml）
/// 然后用dom4j来解析
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Configuration {
    private DataSource dataSource;
    Map<String, MappedStatement> mappedStatementMap=new ConcurrentHashMap<String, MappedStatement>();

    public void setDataSource(DataSourceConfig comboPooledDataSource) {
        this.dataSource =comboPooledDataSource;
    }


}
