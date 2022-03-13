package com.example.lqsentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: OrderController
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/3/13
 **/
@RequestMapping("/order")
@RestController
public class OrderController {


    @PostMapping("")
    @SentinelResource(value = "order")
    public String createOrder(){
        return "创建订单";
    }
}
