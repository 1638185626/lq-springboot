package com.example.lqmybatiscode;

import java.lang.reflect.Method;

/**
 * @className: MapperMethod
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/7/5
 **/
public class MapperMethod {
    private String id;


    public MapperMethod(String id) {
        this.id = id;
    }

    public <T> MapperMethod(Class<T> mapperInterface, Method method, Configuration configuration) {

    }

    public Object execute(SqlSession sqlSession, Object[] args) {
        return sqlSession.selectOne(id, args[0]);
    }
}
