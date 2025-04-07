package com.linbinghui.csdn.util.session;



import com.linbinghui.csdn.util.configuration.Configuration;
import com.linbinghui.csdn.util.configuration.XmlConfigBuilder;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream inputStream) throws Exception {
        // 1.读取配置文件
        //获取configuration对象
        Configuration configuration = new Configuration();
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder(configuration);

        xmlConfigBuilder.parseMyBatisConfig(inputStream);
        //创建sqlSessionFactory
        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        return sqlSessionFactory;
    }
}
