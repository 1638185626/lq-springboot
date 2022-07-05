package com.liuqing.lqmybatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @className: RedisTest
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/6/24
 **/
public class RedisTest {
    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> list = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            int nextInt = random.nextInt(30);
            list.add(nextInt);
            System.out.println(nextInt);
        }
        Map<Integer, List<Integer>> integerListMap = list.stream().collect(Collectors.groupingBy(a -> a));
        System.out.println(integerListMap.toString());
    }
}
