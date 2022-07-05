package com.example.lqmybatiscode;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @className: XMLMapperBuilder
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/7/5
 **/
public class XMLMapperBuilder {
    public void sqlElement(String namespace,String id, String sql, Configuration configuration){
        ResultMap resultMap = configuration.getResultMaps().get(id);
        ParameterMap parameterMap = configuration.getParameterMaps().get(id);
        MappedStatement mappedStatement = new MappedStatement(parameterMap, resultMap,sql);
        configuration.getStatementMap().put(namespace+"."+id,mappedStatement);
    };
    public void parameterMapElement(String id, String parameterType, Configuration configuration) {
        ParameterMap parameterMap = null;
        try {
            parameterMap = new ParameterMap(id, Class.forName(parameterType));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        configuration.getParameterMaps().put(id,parameterMap);
    }
    public void resultMapElement(String id, String resultType, Configuration configuration) {
        Class<?> resultClass = null;
        try {
            resultClass = Class.forName(resultType);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<ResultMapping> propertyResultMappings = new ArrayList<ResultMapping>();
        for (Field field : resultClass.getDeclaredFields()) {
            String name = field.getName();
            propertyResultMappings.add(new ResultMapping(name,name));
        }
        ResultMap resultMap = new ResultMap(id,resultClass,propertyResultMappings);
        configuration.getResultMaps().put(id,resultMap);
    }
}
