package com.example.lqwechat;

import com.example.lqwechat.wechat.http.WeChatHttpCli;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootTest(classes = LqWeChatApplication.class)
class LqWeChatApplicationTests {


    @Resource
    private WeChatHttpCli weChatHttpCli;

    @Test
    void contextLoads() throws IOException {
        String token = weChatHttpCli.getToken();
        System.out.println(token);
    }

}
