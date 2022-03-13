package com.example.lqdingding.controller;

import com.example.lqdingding.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;

public class HelloController {

    @Autowired
    private AsyncService asyncService;


    public void hello(){

        asyncService.executeAsync();
    }
}
