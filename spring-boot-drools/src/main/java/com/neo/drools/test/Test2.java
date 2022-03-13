package com.neo.drools.test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @className: Test2
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/3/11
 **/
public class Test2 {
    public static void main(String[] args) {
//        List<String> strings = Arrays.asList("a","b","a");
//        Map<String, String> stringStringMap = strings.stream().collect(Collectors.toMap(a -> a.toUpperCase(), a -> a.toLowerCase(), (k1, k2) -> k1));
//        System.out.println(stringStringMap);


//        int i = 0;
//        print(i);

        Map<String,String> map = new HashMap<>();
        map.put("a","A");
        map.put("b","B");
        Map<String, String> strMap = map.keySet().stream().collect(Collectors.toMap(String::toUpperCase, a -> map.get(a).toLowerCase(), (k1, k2) -> k1));
        System.out.println(strMap);
    }


    static int print( int i){
        if (i == 100){
            return i;
        }
        System.out.println(i);
        return  print(i + 1);
    }
}
