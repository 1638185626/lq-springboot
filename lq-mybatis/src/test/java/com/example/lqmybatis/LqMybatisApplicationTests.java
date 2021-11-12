package com.example.lqmybatis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class LqMybatisApplicationTests {

    @Resource
    private JuejinJob juejinJob;


    @Test
    void contextLoads() {
        juejinJob.configureTasks();
    }

}
