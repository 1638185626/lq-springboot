package com.order2.rabbitmq.cenum;

/**
 * @className: MqEnum
 * @description: TODO 枚举描述
 * @author: qing liu
 * @date: 2022/3/24
 **/
public enum MqEnum {

    /**
     * 订单队列
     */
    ORDER_CREATE("order.release.order","order-event-exchange"),
    ;


    MqEnum(String queue, String exchange) {
        this.queue = queue;
        this.exchange = exchange;
    }




    /**
     * 队列
     */
    private String queue;
    /**
     * 交换机
     */
    private String exchange;


    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
}
