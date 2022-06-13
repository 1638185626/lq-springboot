package com.order2.rabbitmq.controller;

import com.alibaba.cola.dto.Response;
import com.order2.rabbitmq.cenum.MqEnum;
import com.order2.rabbitmq.service.IOrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: OrderController
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/3/24
 **/
@RestController
@RequestMapping("/order")
public class OrderController {


    @Resource
    private IOrderService iOrderService;


    @PostMapping("/create")
    public Response create(){
        iOrderService.createOrder();
        return Response.buildSuccess();
    }
}
