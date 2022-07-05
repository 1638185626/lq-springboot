package com.example.lqmybatiscode;

/**
 * @className: Environment
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/7/5
 **/
public class Environment {
    private DataSource dataSource;
    public Environment(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public DataSource getDataSource() {
        return dataSource;
    }
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
