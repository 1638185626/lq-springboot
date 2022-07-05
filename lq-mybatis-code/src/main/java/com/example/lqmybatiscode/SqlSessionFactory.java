package com.example.lqmybatiscode;

/**
 * @className: SqlSessionFactory
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/7/5
 **/
public class SqlSessionFactory {
    private Configuration configuration;
    public SqlSessionFactory(Configuration config) {
        this.configuration=config;
    }
    public SqlSession openSession(){
        return new SqlSession(new SimpleExecutor(),configuration);
    };
}
