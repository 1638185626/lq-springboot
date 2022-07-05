package com.example.lqmybatiscode.dao;

import com.example.lqmybatiscode.model.User;

/**
 * @className: UserDAO
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/7/5
 **/
public interface UserDao {

    User getUserById(User user);
}
