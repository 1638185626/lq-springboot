package com.order2.rabbitmq.service;

/**
 * @className: IOrderService
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/3/24
 **/
public interface IOrderService {

    /**
     * 创建订单
     */
    void createOrder();

    /**
     * 关闭订单
     */
    void closeOrder(Long id);
}
