package com.liuqing.starter.config;

import com.liuqing.starter.properties.DemoProperties;
import com.liuqing.starter.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className: DemoConfig
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/3/30
 **/
@Configuration
@EnableConfigurationProperties(DemoProperties.class)
public class DemoConfig {
    @Autowired
    private DemoProperties demoProperties;

    @Bean(name = "demo")
    public DemoService demoService(){
        System.out.println("执行demoService 初始化");
        return new DemoService(demoProperties.getSayWhat(), demoProperties.getToWho());
    }
}
