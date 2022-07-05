package com.example.lqmybatiscode;

import com.example.lqmybatiscode.dao.UserDao;

import java.util.List;

/**
 * @className: SqlSession
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/7/5
 **/
public class SqlSession {
    private SimpleExecutor executor;
    //源码里面是没有Configuration成员变量的，这里是为了方便传参
    private Configuration configuration;
    public SqlSession(SimpleExecutor executor, Configuration configuration) {
        this.executor = executor;
        this.configuration = configuration;
    }
    public <T> T selectOne(String statement, Object parameter) {
        List<T> list = this.selectList(statement, parameter);
        if (list.size() == 1) {
            return list.get(0);
        } else {
            return null;
        }
    }
    private <T> List<T> selectList(String statement, Object parameter) {
        List var5;
        try {
            MappedStatement ms = configuration.getMappedStatement(statement);
            //mybatis这里还执行了很多代码，判断语句，缓存优化等。
            var5 = this.executor.query(ms, parameter,configuration);
        } catch (Exception var9) {
            var9.printStackTrace();
            throw new RuntimeException("查询失败...");
        }
        return var5;
    }
    public <T> T getMapper(Class<UserDao> type) {
        return configuration.getMapper(type, this);
    }
    public Configuration getConfiguration() {
        return configuration;
    }
    public SimpleExecutor getExecutor() {
        return executor;
    }
}
