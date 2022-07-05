package com.example.lqmybatiscode;

import com.example.lqmybatiscode.dao.UserDao;
import com.example.lqmybatiscode.model.User;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.io.InputStream;

/**
 * @className: MybatisTest
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/7/5
 **/
public class MybatisTest {
    public static void main(String[] args) throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession session = sqlSessionFactory.openSession();
        //mybatis只有在执行sql时候才会连接数据库,所以手写框架也是在这里面先连接数据库
        //后执行sql
        //(1)直接使用session的方法进行执行，传参需要传mapper.xml中sql的id（namespace+id）
        User userInfo  = session.selectOne("com.example.lqmybatiscode.dao.UserDao.getUserById",1);
        //(2)session获取mapper的代理对象，让代理对象执行，这也是日常开发中是用mybatis后台的实际操作流程
        UserDao mapper = session.getMapper(UserDao.class);
        User myInfo1 = mapper.getUserById(new User());
        System.out.println(userInfo.toString());
        System.out.println(myInfo1.toString());
    }
}
