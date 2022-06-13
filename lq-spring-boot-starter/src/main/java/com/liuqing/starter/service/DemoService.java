package com.liuqing.starter.service;

import org.springframework.stereotype.Component;

/**
 * @className: DemoService
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/3/30
 **/
public class DemoService {
    public String sayWhat;
    public String toWho;
    public DemoService(String sayWhat, String toWho){
        this.sayWhat = sayWhat;
        this.toWho = toWho;
    }
    public String say(){
        return this.sayWhat + "!  " + toWho;
    }
}
