package com.example.lqspringbootrocketmq.controller;

import com.example.lqspringbootrocketmq.entity.User;
import com.example.lqspringbootrocketmq.service.MQProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msg")
public class HelloController {


    @Autowired
    private MQProducerService mqProducerService;

    @GetMapping("/send")
    public String sendMsg(){

        User user = new User();
        user.setId("00001");
        mqProducerService.send(user);
        return "发送成功";
    }
}
