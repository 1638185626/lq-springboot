package com.example.lqmybatiscode.model;

import lombok.Data;
import lombok.ToString;

/**
 * @className: User
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/7/5
 **/
@Data
public class User {

    private Long id;
    private String username;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"username\":\"")
                .append(username).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
