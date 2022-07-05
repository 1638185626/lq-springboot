package com.example.lqmybatiscode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * @className: SimpleExecutor
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/7/5
 **/
public class SimpleExecutor {

    public <E> List<E> query(MappedStatement ms, Object parameter, Configuration configuration) {
        List list = this.queryFromDatabase(ms, parameter,configuration);
        return list;
    }
    private List queryFromDatabase(MappedStatement ms, Object parameter, Configuration configuration) {
        List list = this.doQuery(ms, parameter,configuration);
        return list;
    }
    private List doQuery(MappedStatement ms, Object parameter, Configuration configuration) {
        List var9;
        //mybatis返回的是sql语句对象Statement，但是prepareStatement方法里面真正连接了数据库
        //所以为了简化书写，这里直接返回了Connection
        Connection connection = this.prepareStatement(configuration.getEnvironment());
        //mybatis这里是用StatementHandler对象的query方法，执行查询
        //为了书写方便，我直接传了jdbc的connection，源码里面并不是这么写的
        SimpleStatementHandler simpleStatementHandler = new SimpleStatementHandler();
        var9 = simpleStatementHandler.query(connection,ms,parameter);
        return var9;
    }
    private Connection prepareStatement(Environment environment) {
        //因为mybatis是对jdbc的封装，所以直接用jdbc的原生代码生成
        Connection connection=null;
        DataSource dataSource = environment.getDataSource();
        try {
            Class.forName(dataSource.getDriver()); //加载对应驱动
            connection = (Connection) DriverManager.getConnection(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
