package com.example.lqmybatis;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author liuqing01
 * @version 1.0
 * @description TODO
 * @date 2021/11/9 14:51
 */
@RestController
@RequestMapping
public class HelloWorld {



    @GetMapping("/hello")
    public String hello(@RequestParam  Map map){
        return new Gson().toJson(map);
    }
}
