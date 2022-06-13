package com.inventory.rabbitmq.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: InventoryController
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/3/24
 **/
@RequestMapping("/inventory")
@RestController
public class InventoryController {


    /**
     * 扣减库存
     * @return
     */
    @PostMapping("/")
    public String create(){
        return "";
    }
}
