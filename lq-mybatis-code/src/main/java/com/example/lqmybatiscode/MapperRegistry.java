package com.example.lqmybatiscode;

import com.example.lqmybatiscode.dao.UserDao;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: MapperRegistry
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/7/5
 **/
public class MapperRegistry {
    //源码中是个map，存放扫描到的mapper接口类
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();
    public Map<Class<?>, MapperProxyFactory<?>> getKnownMappers() {
        return knownMappers;
    }
    public <T> T getMapper(Class<UserDao> type, SqlSession sqlSession) {
        if (!knownMappers.containsKey(type)){
            throw new RuntimeException("该mapper接口未被注册");
        }
        MapperProxyFactory<?> mapperProxyFactory = knownMappers.get(type);
        return (T) mapperProxyFactory.newInstance(sqlSession);
    }
}
