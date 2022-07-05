package com.example.lqmybatiscode;

/**
 * @className: ParameterMap
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/7/5
 **/
public class ParameterMap {
    private String id;
    private Class<?> type;
    public ParameterMap(String id, Class<?> type) {
        this.id = id;
        this.type = type;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Class<?> getType() {
        return type;
    }
    public void setType(Class<?> type) {
        this.type = type;
    }
}
