package com.liuqing.starter.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @className: DemoProperties
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/3/30
 **/
@Data
@ConfigurationProperties(prefix = "demo")
public class DemoProperties{
    private String sayWhat;
    private String toWho;

    public String getSayWhat() {
        return sayWhat;
    }

    public void setSayWhat(String sayWhat) {
        this.sayWhat = sayWhat;
    }

    public String getToWho() {
        return toWho;
    }

    public void setToWho(String toWho) {
        this.toWho = toWho;
    }
}
