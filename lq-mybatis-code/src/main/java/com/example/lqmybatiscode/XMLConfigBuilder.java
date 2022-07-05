package com.example.lqmybatiscode;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @className: XMLConfigBuilder
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/7/5
 **/
public class XMLConfigBuilder {
    private InputStream inputStream;
    public XMLConfigBuilder(InputStream inputStream) {
        this.inputStream = inputStream;
    }
    public Configuration parse() {
        SAXReader reader = new SAXReader();
        Configuration configuration = null;
        try {
            Document document = reader.read(inputStream);
            Element rootElement = document.getRootElement();
            //原来的mybatis是执行parseConfiguration方法后，在XMLConfigBuilder的父类属性Configuration进行赋值
            //这里简化书写，直接返回Configuration
            configuration = this.parseConfiguration(rootElement);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return configuration;
    }
    private Configuration parseConfiguration(Element rootElement) {
        //简化书写，就解析出mapper、environment标签,源码其实还解析了xml中的很多其他东西
        Configuration configuration = new Configuration();
        Element dataSource = rootElement.element("environments").element("environment").element("dataSource");
        Element mapper = rootElement.element("mappers").element("mapper");
        this.environmentsElement(configuration, dataSource);
        this.mapperElement(configuration, mapper);
        return configuration;
    }
    private void environmentsElement(Configuration configuration, Element dataSource) {
        Iterator iterator = dataSource.elementIterator();
        DataSource dataSource1 = new DataSource();
        while (iterator.hasNext()) {
            Element element = (Element) iterator.next();
            String name = element.attribute("name").getValue();
            String value = element.attribute("value").getValue();
            if (name.equals("driver")) {
                dataSource1.setDriver(value);
            } else if (name.equals("url")) {
                dataSource1.setUrl(value);
            } else if (name.equals("username")) {
                dataSource1.setUsername(value);
            } else if (name.equals("password")) {
                dataSource1.setPassword(value);
            }
        }
        configuration.setEnvironment(new Environment(dataSource1));
    }
    private void mapperElement(Configuration configuration, Element mapper) {
        //解析mapper.xml,源码中主要是调用了XMLMapperBuilder的parse方法进一步执行业务，这里做了简化
        try {
            Attribute resource = mapper.attribute("resource");
            String resourceValue = resource.getValue();
            InputStream inputStream = ClassLoader.getSystemResourceAsStream(resourceValue);
            SAXReader saxReader = new SAXReader();
            if(inputStream == null){
               try {
                   inputStream = new FileInputStream(new File("/Users/liuqing/ideaProject/lq-springboot/lq-mybatis-code/src/main/resources/UserDao.xml"));
               }catch (Exception e){
                   e.printStackTrace();
               }
            }
            Document document = saxReader.read(inputStream);
            Element rootElement = document.getRootElement();
            //注册扫描到的mapper接口
            String namespace = rootElement.attribute("namespace").getValue();
            configuration.setMapperRegistry(new MapperRegistry());
            Class<?> mapperClass = null;
            try {
                mapperClass = Class.forName(namespace);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            MapperProxyFactory<?> mapperProxyFactory = new MapperProxyFactory<>(mapperClass);
            //接口方法与sql对应还没注册，在后面解析会进行。
            configuration.getMapperRegistry().getKnownMappers().put(mapperClass, mapperProxyFactory);
            configuration.setParameterMaps(new HashMap<String, ParameterMap>());
            configuration.setResultMaps(new HashMap<String, ResultMap>());
            configuration.setMappedStatements(new HashMap<String, MappedStatement>());
            //解析mapper.xml的解析器
            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder();
            Iterator iterator = rootElement.elementIterator();
            while (iterator.hasNext()) {
                Element element = (Element) iterator.next();
                String sqlType = element.getQualifiedName();
                String id = element.attribute("id").getValue();
                String resultType = element.attribute("resultType").getValue();
                String parameterType = element.attribute("parameterType").getValue();
                String sql = element.getTextTrim();
                //注册接口方法和sql的对应关系
                for (Method method : mapperClass.getDeclaredMethods()) {
                    if (method != null && method.getName().equals(id)) {
                        MapperProxyFactory<?> mapperProxyFactory1 = configuration.getMapperRegistry().getKnownMappers().get(mapperClass);
                        mapperProxyFactory1.getMethodCache().put(method,new MapperMethod(namespace+"."+id));
                    }
                }
                //注册该sql的参数
                xmlMapperBuilder.parameterMapElement(id, parameterType, configuration);
                //注册该sql的返回封装类
                xmlMapperBuilder.resultMapElement(id, resultType, configuration);
                //注册该sql
                xmlMapperBuilder.sqlElement(namespace, id, sql, configuration);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
