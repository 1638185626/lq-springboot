package com.qing.observer;

import org.junit.Test;

/**
 * @className: Observer1
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/3/15
 **/
public class Observer1Test {


    @Test
    public void observe() {
        //模拟一个3D的服务号
        ObjectFor3D subjectFor3d = new ObjectFor3D();
        //客户1
        Observer observer1 = new Observer1(subjectFor3d);
        Observer observer2 = new Observer2(subjectFor3d);

        subjectFor3d.setMsg("20140420的3D号码是：127");
        subjectFor3d.setMsg("20140421的3D号码是：333");

    }

}
