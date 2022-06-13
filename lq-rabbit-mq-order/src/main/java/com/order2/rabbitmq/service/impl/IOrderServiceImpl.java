package com.order2.rabbitmq.service.impl;

import com.alibaba.cola.exception.BizException;
import com.google.gson.Gson;
import com.order2.rabbitmq.cenum.MqEnum;
import com.order2.rabbitmq.entity.OmsOrder;
import com.order2.rabbitmq.service.IOrderService;
import com.order2.rabbitmq.utils.SnowflakeIdWorker;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: IOrderService
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/3/24
 **/
@Service
public class IOrderServiceImpl implements IOrderService {


    @Resource
    private SnowflakeIdWorker snowflakeIdWorker;

    @Resource
    private RabbitTemplate rabbitTemplate;
    /**
     * 创建订单
     */
    @Override
    public void createOrder() {
        // 创建订单
        OmsOrder order = new OmsOrder();
        order.setId(snowflakeIdWorker.nextId());
        // 远程调用库存服务，锁定库存
//        R r = wareFeignService.orderLockStock(wareSkuLockVo);
        if (0 == 0) {
            // 锁定成功，发送订单创建消息到延时队列
            rabbitTemplate.convertAndSend("order-event-exchange","order.create.order",new Gson().toJson(order));
        } else {
            // 远程调用失败，抛出异常
            throw new BizException("扣减库存失败！");
        }
//        System.out.println("OrderController.create««eate");
//        rabbitTemplate.convertAndSend(MqEnum.ORDER_CREATE.getExchange(),MqEnum.ORDER_CREATE.getQueue(),order);
    }

    /**
     * 关闭订单
     *
     * @param id
     */
    @Override
    public void closeOrder(Long id) {
        System.out.println("查询当前订单是否付款");
        // 查询当前订单是否付款
        // OmsOrderEntity order = getOne(new QueryWrapper<OmsOrderEntity>().eq("biz_order_id", bizOrderId));
//        if (order != null) {
//            // 判断订单状态，为新建状态才取消
//            if (order.getOrderStatus() == OrderStatusConstant.CREATE.getCode()){
//                //过期未支付，取消订单，设置订单状态为取消状态
//                updateOrder.setOrderStatus(OrderStatusConstant.CANCEL.getCode());
//                // 发送MQ
//                rabbitTemplate.convertAndSend("order-event-exchange","order.release.other",orderTo);
//            }
//        }
    }
}
