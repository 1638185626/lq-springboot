package com.example.lqredis;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashSet;
import java.util.Set;

/**
 * @author liuqing01
 * @version 1.0
 * @description: TODO
 * @date 2021/10/29 16:56
 */
public class TestMain {


    public static void main(String[] args) {
    }



    public static void add(Long num){
        for (int i = 0; i < 100; i++) {
            num = num + 1;
        }
    }
}
