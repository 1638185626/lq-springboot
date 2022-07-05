package com.example.lqmybatiscode;

/**
 * @className: MappedStatement
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/7/5
 **/
public class MappedStatement {
    private ParameterMap parameterMap;
    private ResultMap resultMaps;
    private String sql;
    public MappedStatement(ParameterMap parameterMap, ResultMap resultMaps, String sql) {
        this.parameterMap = parameterMap;
        this.resultMaps = resultMaps;
        this.sql = sql;
    }
    public ParameterMap getParameterMap() {
        return parameterMap;
    }
    public void setParameterMap(ParameterMap parameterMap) {
        this.parameterMap = parameterMap;
    }
    public ResultMap getResultMaps() {
        return resultMaps;
    }
    public void setResultMaps(ResultMap resultMaps) {
        this.resultMaps = resultMaps;
    }
    public String getSql() {
        return sql;
    }
    public void setSql(String sql) {
        this.sql = sql;
    }
}
