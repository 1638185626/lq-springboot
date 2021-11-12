package com.example.prometheus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuqing01
 * @version 1.0
 * @description 用户接口
 * @date 2021/11/11 19:57
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @GetMapping("/hello")
    public String hello(){
        return "{}";
    }
}
