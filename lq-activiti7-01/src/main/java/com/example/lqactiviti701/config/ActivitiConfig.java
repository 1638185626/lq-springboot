package com.example.lqactiviti701.config;

import com.example.lqactiviti701.utils.MyIdGenerator;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.context.annotation.Configuration;


/**
 * @className: ActivitiConfig
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/6/15
 **/
@Configuration
public class ActivitiConfig implements ProcessEngineConfigurationConfigurer {

    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        springProcessEngineConfiguration.setIdGenerator(new MyIdGenerator());
    }
}
