package com.linbinghui.csdn.util.configuration;

import com.linbinghui.csdn.util.connectionpool.DataSourceConfig;
import com.linbinghui.csdn.util.io.Resources;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

//解析mybatis的核心配置文件 mybatis-config.xml
public class XmlConfigBuilder {
    private Configuration configuration;

    public XmlConfigBuilder() {
    }
    public XmlConfigBuilder(Configuration configuration) {
        this.configuration = configuration;
    }
//parse是解析的意思
    public Configuration parseMyBatisConfig(InputStream inputStream) throws DocumentException, PropertyVetoException {
        SAXReader saxReader=new SAXReader();
        Document document = saxReader.read(inputStream);
        Element rootElement = document.getRootElement();
        /*List<Element> propertyList = rootElement.selectNodes("//property");
        Properties properties=new Properties();
        for (Element element : propertyList) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name,value);
        }*/
        List<Node> propertyList = rootElement.selectNodes("//property");
        Properties properties = new Properties();
        for (Node node : propertyList) {
            if (node instanceof Element) {
                Element element = (Element) node;
                String name = element.attributeValue("name");
                String value = element.attributeValue("value");
                properties.setProperty(name, value);
            }
        }
        //初始化一个数据连接池
        /*ComboPooledDataSource comboPooledDataSource=new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(properties.getProperty("driverClass"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        comboPooledDataSource.setUser(properties.getProperty("username"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));*/
        DataSourceConfig dataSourceConfig=new DataSourceConfig();
        dataSourceConfig.setDriver(properties.getProperty("driverClass"));
        dataSourceConfig.setUrl(properties.getProperty("jdbcUrl"));
        dataSourceConfig.setUsername(properties.getProperty("username"));
        dataSourceConfig.setPassword(properties.getProperty("password"));
        //设置数据库连接池
        configuration.setDataSource(dataSourceConfig);
        //mybatis的核心配置文件中，映射xxxxMapper.xml文件（因为可能有多个mapper，所以用一个list集合存起来）
        List<Node> mapperList = rootElement.selectNodes("//mapper");
        for (Node element : mapperList) {
            Element element1 = (Element) element;
           String resource = element1.attributeValue("resource");
           InputStream resourceAsStream = Resources.GetResourceAsStream(resource);
           if (resourceAsStream==null){
               System.out.println("resource");
               System.out.println("输入流为null");
           }
           XmlMapperBuilder xmlMapperBuilder=new XmlMapperBuilder(configuration);
           xmlMapperBuilder.parse(resourceAsStream);

        }
        return configuration;
    }
}
