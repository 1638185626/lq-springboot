package com.maps;


import lombok.Data;

import java.util.StringJoiner;

/**
 * @className: CarDto
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/3/20
 **/
@Data
public class CarDto {

    private String make;
    private int seatCount;
    private String type;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"make\":\"")
                .append(make).append('\"');
        sb.append(",\"seatCount\":")
                .append(seatCount);
        sb.append(",\"type\":\"")
                .append(type).append('\"');
        sb.append('}');
        return sb.toString();
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMake() {
        return make;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public String getType() {
        return type;
    }

}
