package com.liuqing.rabbitmq;

//import com.order2.rabbitmq.mq.CustomSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitMqOrderApplicationTests {

    @Test
    void contextLoads() {
    }

//    @Autowired
//    private CustomSender customSender;
//
//    @Test
//    public void send() throws Exception
//    {
//        //发送消息
//        customSender.sendMsg("支付超时，取消订单通知！");
//
//        //程序延时15秒，否则程序立即执行完毕，则控制台无法看到消息队列延迟的结果
//        Thread.sleep(15000);
//    }

}
