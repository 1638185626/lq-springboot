package com.liuqing.lqmybatis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootTest
class LqMybatisApplicationTests {

    @Resource
    private JuejinJob juejinJob;


    @Test
    void contextLoads() {
        try {
            juejinJob.configureTasks();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    void threadTest(){
        Thread start = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "线程名称");
        });
        start.start();
    }




}
