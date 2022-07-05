package com.example.lqmybatiscode;

import java.io.InputStream;

/**
 * @className: SqlSessionFactoryBuilder
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/7/5
 **/
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream resourceAsStream) {
        Configuration configuration = new XMLConfigBuilder(resourceAsStream).parse();
        return new SqlSessionFactory(configuration);
    }
}
