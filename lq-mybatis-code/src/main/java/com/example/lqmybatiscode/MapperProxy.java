package com.example.lqmybatiscode;


import org.apache.ibatis.reflection.ExceptionUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @className: MapperProxy
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/7/5
 **/
public class MapperProxy<T> implements InvocationHandler {
    private static final long serialVersionUID = -6424540398559729838L;
    private final SqlSession sqlSession;
    private final Class<T> mapperInterface;
    private final Map<Method, MapperMethod> methodCache;
    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface, Map<Method, MapperMethod> methodCache) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
        this.methodCache = methodCache;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 代理以后，所有的mapper的方法调用时，都会调用invoke方法
        if (Object.class.equals(method.getDeclaringClass())){
            try {
                return method.invoke(this,args);
            }catch (Throwable t){
                throw ExceptionUtil.unwrapThrowable(t);
            }
        }
        // 从缓存中找 MaperMethod
        final MapperMethod mapperMethod = calculateMapperMethod(method, args);
        return mapperMethod.execute(sqlSession, args);
    }
    // 去缓存中查找
    private MapperMethod calculateMapperMethod(Method method, Object[] args) {
        MapperMethod mapperMethod = methodCache.get(method);
        if (mapperMethod == null) {
            //找不到才去new
            mapperMethod = new MapperMethod(mapperInterface, method, sqlSession.getConfiguration());
            methodCache.put(method, mapperMethod);
        }
        return mapperMethod;
    }
}
