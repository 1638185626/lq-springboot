package com.qing.observer2;

/**
 * @className: Observer
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/3/15
 **/
public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
