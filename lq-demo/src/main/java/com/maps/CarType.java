package com.maps;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @className: CarType
 * @description: TODO 枚举描述
 * @author: qing liu
 * @date: 2022/3/20
 **/

public enum CarType {

    /**
     * 枚举查询
     */
    SEDAN(1,"账户上");

    private int code;

    private String message;

    CarType(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
