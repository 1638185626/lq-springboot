package com.liuqing.lqmybatis;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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


    @Autowired
    private JuejinJob juejinJob;


    @GetMapping("/hello")
    public String hello(@RequestParam  Map map) throws IOException {
        juejinJob.configureTasks();
        return new Gson().toJson(map);
    }
}
