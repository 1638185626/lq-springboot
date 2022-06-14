package com.qing.func;

import java.util.StringTokenizer;
import java.util.function.Supplier;

/**
 * @className: DemoSupplier
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/6/14
 **/
public class DemoSupplier {
    private static final String getString(Supplier<String> func){
        return func.get();
    }

    public static void main(String[ ] args) {
        String msgA = "hello";
        String msgB = "world";
        System.out.println(getString(() -> msgA + msgB));

    }
}
