//package com.order2.rabbitmq.mq;
//
//import org.springframework.amqp.AmqpException;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessagePostProcessor;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * @author liuqing01
// * @version 1.0
// * @description 生产者
// * @date 2021/11/2 11:00
// */
//@Service
//public class CustomSender {
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    public void sendMsg(String msg)
//    {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println("消息发送时间：" + sdf.format(new Date()));
//        rabbitTemplate.convertAndSend(RabbitMqConfig.DELAY_EXCHANGE_NAME, RabbitMqConfig.DELAY_ROUTING_KEY, msg, new MessagePostProcessor()
//        {
//            @Override
//            public Message postProcessMessage(Message message) throws AmqpException
//            {
//                // 消息延迟5秒
//                åmessage.getMessageProperties().setHeader("x-delay", 5000);
//                return message;
//            }
//        });
//    }
//
//}
