package com.example.lqmybatiscode;

/**
 * @className: ResultMapping
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/7/5
 **/
public class ResultMapping {
    private String property;
    private String column;
    public ResultMapping(String property, String column) {
        this.property = property;
        this.column = column;
    }
    public String getProperty() {
        return property;
    }
    public void setProperty(String property) {
        this.property = property;
    }
    public String getColumn() {
        return column;
    }
    public void setColumn(String column) {
        this.column = column;
    }
}
