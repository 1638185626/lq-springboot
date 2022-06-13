package com.inventory.rabbitmq.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @className: MyRabbitTemplate
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/3/24
 **/
@Component
public class MyRabbitTemplate {


    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void initRabbitTemplate(){
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            /**
             *
             * @param correlationData 当前消息的唯一关联数据
             * @param b 消息是否成功收到
             * @param s 失败的原因
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                System.out.println("当前消息【"+correlationData+"】==》服务端是否收到："+b+"==》失败的原因【"+s+"】");
            }
        });
    }



    @PostConstruct
    public void init2RabbitTemplate(){

        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            /**
             * 只要消息没有投递给指定的队列，就触发这个失败回调
             * @param message 投递失败的消息详细信息
             * @param i 回复状态码
             * @param s 回复的文本内容
             * @param s1 当时这个消息发送给哪个交换机
             * @param s2 当时这个消息用哪个路由键
             */
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                System.out.println("失败信息【"+message+"】==》状态码【"+i+"】==》文本内容【"+s+"】==》交换机【"+s1+"】==》路由键【"+s2+"】");
            }
        });
    }

}
