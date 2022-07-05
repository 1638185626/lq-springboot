package com.example.lqactiviti701.utils;

import org.activiti.engine.impl.cfg.IdGenerator;

import java.util.UUID;

/**
 * @className: idGenerator
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/6/15
 **/
public class MyIdGenerator implements IdGenerator {
    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * Activiti ID 生成
     */
    @Override
    public String getNextId() {
        return uuid();
    }

}
