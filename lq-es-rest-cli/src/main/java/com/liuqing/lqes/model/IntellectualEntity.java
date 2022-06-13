package com.liuqing.lqes.model;

import lombok.Data;

/**
 * @className: IntellectualEntity
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/4/24
 **/
@Data
public class IntellectualEntity {

    private Long id;

    private String name;

    private Integer type;

    private String keycode;

    private String officeId;

    private String officeName;

    private String titular;

    private Long applyTime;

    private Long endTime;

    private String status;

    private String agentName;
}
