package com.example.lqkafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @className: UserLogConsumer
 * @description: TODO 类描述
 * @author: liuqing
 * @date: 2021/12/28
 **/
@Slf4j
@Component
public class UserLogConsumer {

    @KafkaListener(topics = "test")
    public void listen(@Payload String message){
        log.info("received message={}",message);
    }
}
