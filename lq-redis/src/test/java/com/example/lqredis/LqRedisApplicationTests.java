package com.example.lqredis;

import com.example.lqredis.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
@SpringBootTest(classes = LqRedisApplication.class)
class LqRedisApplicationTests {

    @Resource
    private RedisUtil redisUtil;

    private String key = "redis-key";


    @Test
    void contextLoads() {
        for (int i = 0; i < 100; i++) {
            redisUtil.incrBy(key,1);
        }
    }

}
