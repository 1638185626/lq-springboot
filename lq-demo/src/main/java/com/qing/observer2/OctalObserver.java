package com.qing.observer2;

/**
 * @className: OctalObserver
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/3/15
 **/
public class OctalObserver extends Observer{

    public OctalObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Octal String: "
                + Integer.toOctalString( subject.getState() ) );
    }
}
