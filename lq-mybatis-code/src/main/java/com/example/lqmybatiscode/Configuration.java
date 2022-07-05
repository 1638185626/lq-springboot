package com.example.lqmybatiscode;

import com.example.lqmybatiscode.dao.UserDao;

import java.util.Map;

/**
 * @className: Configuration
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/7/5
 **/
public class Configuration {
    //封装数据源的环境
    private Environment environment;
    //返回结果映射
    private Map<String, ResultMap> resultMaps;
    //参数映射
    private Map<String, ParameterMap> parameterMaps;
    //Mapper.xml存的sql  key=namespace+id
    private Map<String, MappedStatement> mappedStatements;
    //mapper接口
    private MapperRegistry mapperRegistry;
    public Map getStatementMap() {
        return mappedStatements;
    }
    public MappedStatement getMappedStatement(String statement) {
        if (mappedStatements.get(statement) == null) {
            throw new RuntimeException("mapper.xml文件找不到对应sql语句");
        }
        return mappedStatements.get(statement);
    }
    public Environment getEnvironment() {
        return environment;
    }
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
    public Map<String, ResultMap> getResultMaps() {
        return resultMaps;
    }
    public void setResultMaps(Map<String, ResultMap> resultMaps) {
        this.resultMaps = resultMaps;
    }
    public Map<String, ParameterMap> getParameterMaps() {
        return parameterMaps;
    }
    public void setParameterMaps(Map<String, ParameterMap> parameterMaps) {
        this.parameterMaps = parameterMaps;
    }
    public Map<String, MappedStatement> getMappedStatements() {
        return mappedStatements;
    }
    public void setMappedStatements(Map<String, MappedStatement> mappedStatements) {
        this.mappedStatements = mappedStatements;
    }
    public MapperRegistry getMapperRegistry() {
        return mapperRegistry;
    }
    public void setMapperRegistry(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }
    public <T> T getMapper(Class<UserDao> type, SqlSession sqlSession) {
        return this.mapperRegistry.getMapper(type, sqlSession);
    }
}
