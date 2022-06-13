package com.order2.rabbitmq;

import com.order2.rabbitmq.utils.SnowflakeIdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@MapperScan("com.order2.rabbitmq.dao")
@SpringBootApplication
public class RabbitMqOrderApplication {


    @Bean
    public SnowflakeIdWorker  snowflakeIdWorker(){
        return new SnowflakeIdWorker(1,1);
    }

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqOrderApplication.class, args);
    }

}
