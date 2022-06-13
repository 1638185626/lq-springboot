package com.order2.rabbitmq.event;

import com.order2.rabbitmq.cenum.MqEnum;
import com.order2.rabbitmq.entity.OmsOrder;
import com.order2.rabbitmq.service.IOrderService;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @className: OrderCloseListener
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/3/25
 **/
@RabbitListener(queues = "order.delay.queue")
@Service
public class OrderCloseListener {

    @Autowired
    IOrderService iOrderService;

    @RabbitHandler
    public void listener(OmsOrder orderEntity, Channel channel, Message message) throws IOException {
        System.out.println("收到过期订单，准备关单：" + orderEntity.getId());
        try{
            iOrderService.closeOrder(orderEntity.getId());
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch (Exception e){
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }
}
