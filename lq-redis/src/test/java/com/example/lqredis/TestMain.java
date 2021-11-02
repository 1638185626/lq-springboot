package com.example.lqredis;

/**
 * @author liuqing01
 * @version 1.0
 * @description: TODO
 * @date 2021/10/29 16:56
 */
public class TestMain {


    public static void main(String[] args) {
        Long a = 0L;
        add(a);

        System.out.println("a===" + a);
    }



    public static void add(Long num){
        for (int i = 0; i < 100; i++) {
            num = num + 1;
        }
    }
}
