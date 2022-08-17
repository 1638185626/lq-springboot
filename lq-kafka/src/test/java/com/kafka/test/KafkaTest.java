package com.kafka.test;

import com.liuqing.KafkaApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;


@SpringBootTest(classes = KafkaApplication.class)
public class KafkaTest {

    @Autowired
    private KafkaTemplate kafkaTemplate;


    @Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            kafkaTemplate.send("test","tencent\tCN\tE\t2020-07-21 22:15:02\t117.59.39.169\tml.qq.com\t867\t");
            kafkaTemplate.flush();
            System.out.println("发送完成11");
        }
    }
}
