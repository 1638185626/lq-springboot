package com.qing.observer2;

import com.qing.observer2.HexaObserver;
import com.qing.observer2.OctalObserver;
import org.junit.Test;

/**
 * @className: Observer1
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/3/15
 **/
public class Observer2Test {


    @Test
    public void observe() {
        Subject subject = new Subject();

        new HexaObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);

    }

}
