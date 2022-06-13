package com.liuqing.lqdingding.controller;

import com.liuqing.lqdingding.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;

public class HelloController {

    @Autowired
    private AsyncService asyncService;


    public void hello(){

        asyncService.executeAsync();
    }
}
