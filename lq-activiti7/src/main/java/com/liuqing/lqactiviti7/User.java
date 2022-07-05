package com.liuqing.lqactiviti7;

import jdk.jfr.DataAmount;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * @className: User
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/6/15
 **/
@Data
public class User {

    private Integer id;

    private List authorities;

    private String username;

    private String password;
}
