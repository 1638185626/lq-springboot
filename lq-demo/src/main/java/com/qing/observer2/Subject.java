package com.qing.observer2;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: Subject
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/3/15
 **/
public class Subject {
    private List<Observer> observers
            = new ArrayList<Observer>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
