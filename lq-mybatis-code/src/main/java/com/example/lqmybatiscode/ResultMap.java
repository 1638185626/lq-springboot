package com.example.lqmybatiscode;

import java.util.List;

/**
 * @className: ResultMap
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/7/5
 **/
public class ResultMap {
    private String id;
    private Class<?> type;
    private List<ResultMapping> propertyResultMappings;
    public ResultMap() {
    }
    public ResultMap(String id, Class<?> type, List<ResultMapping> propertyResultMappings) {
        this.id = id;
        this.type = type;
        this.propertyResultMappings = propertyResultMappings;
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
    public List<ResultMapping> getPropertyResultMappings() {
        return propertyResultMappings;
    }
    public void setPropertyResultMappings(List<ResultMapping> propertyResultMappings) {
        this.propertyResultMappings = propertyResultMappings;
    }
}
