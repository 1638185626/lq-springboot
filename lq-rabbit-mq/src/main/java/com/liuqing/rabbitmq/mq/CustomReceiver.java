package com.liuqing.rabbitmq.mq;

import com.liuqing.rabbitmq.config.RabbitMqConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liuqing01
 * @version 1.0
 * @description 消费
 * @date 2021/11/2 11:01
 */
@Component
public class CustomReceiver {


    @RabbitListener(queues = RabbitMqConfig.DELAY_QUEUE_NAME)
    public void receive(String msg)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date())+msg);
        System.out.println("Receiver：执行取消订单");
    }
}
