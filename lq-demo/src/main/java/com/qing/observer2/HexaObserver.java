package com.qing.observer2;

/**
 * @className: HexaObserver
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/3/15
 **/
public class HexaObserver extends Observer{
    public HexaObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Hex String: "
                + Integer.toHexString( subject.getState() ).toUpperCase() );
    }
}
