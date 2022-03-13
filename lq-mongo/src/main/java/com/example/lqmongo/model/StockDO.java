package com.example.lqmongo.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author liuqing
 */
@Data
@Document(collection = "stock")
public class StockDO {
    /**
     * 代码
     */
    private String symbol;
    /**
     * 编码
     */
    private String code;
    /**
     * 股票名称
     */
    private String name;
    /**
     * 最新价
     */
    private Integer trade;
    /**
     * 涨跌额
     */
    private Double pricechange;
    /**
     * 涨跌幅
     */
    private Double  changepercent;
    /**
     * 买入
     */
    private Double buy;
    /**
     * 卖出
     */
    private Double sell;
    /**
     * 昨收
     */
    private Double settlement;
    /**
     * 今开
     */
    private Double open;


}
