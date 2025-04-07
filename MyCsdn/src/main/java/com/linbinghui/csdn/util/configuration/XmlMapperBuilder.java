package com.linbinghui.csdn.util.configuration;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//使用dom4j解析XML配置文件,(usermapper.xml)
public class XmlMapperBuilder {
    //配置数据封装对象
    private Configuration configuration;
    public XmlMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }
    //把配置文件usermapper.xml当作一个流传进来
    public void parse(InputStream inputStream) throws DocumentException {
        //1.获取dom4j解析xml文件
        SAXReader saxReader=new SAXReader();
        //xml文档对象
        Document document = saxReader.read(inputStream);
        Element rootElement = document.getRootElement();
        //获取节点属性
        Attribute attributeNamespace = rootElement.attribute("namespace");
        String namespace = attributeNamespace.getValue();
        //获取所有的查询、添加删除更新等sql语句，并返回一个集合
        List<Node> selectList = rootElement.selectNodes("//select");
        List<Node> insertList = rootElement.selectNodes("//insert");
        List<Node> updateList = rootElement.selectNodes("//update");
        List<Node> deleteList = rootElement.selectNodes("//delete");

        // 将 List<Node> 转换为 List<Element>
        List<Element> selectElementList = selectList.stream()
                .filter(node -> node instanceof Element)
                .map(node -> (Element) node)
                .collect(Collectors.toList());

        List<Element> insertElementList = insertList.stream()
                .filter(node -> node instanceof Element)
                .map(node -> (Element) node)
                .collect(Collectors.toList());

        List<Element> updateElementList = updateList.stream()
                .filter(node -> node instanceof Element)
                .map(node -> (Element) node)
                .collect(Collectors.toList());

        List<Element> deleteElementList = deleteList.stream()
                .filter(node -> node instanceof Element)
                .map(node -> (Element) node)
                .collect(Collectors.toList());

        List<Element> allList=new ArrayList<Element>();
        allList.addAll(selectElementList);
        allList.addAll(insertElementList);
        allList.addAll(updateElementList);
        allList.addAll(deleteElementList);

        for (Element element : allList){
            //每条sql的id
        String id=element.attributeValue("id");
            //获取返回值类型
            String resultType=element.attributeValue("resultType");
            //参数类型
            String parameterType=element.attributeValue("parameterType");
            //获取sql语句(Trim去掉空格）
            String sqlText=element.getTextTrim();
            //封装对象
            MappedStatement mappedStatement=new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParameterType(parameterType);
            mappedStatement.setSql(sqlText);

            String key=namespace+"."+id;
            configuration.getMappedStatementMap().put(key,mappedStatement);
        }


    }
}
