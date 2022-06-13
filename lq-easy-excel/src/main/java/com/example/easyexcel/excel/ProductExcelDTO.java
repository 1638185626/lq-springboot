package com.example.easyexcel.excel;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

/**
 * @className: ProductExcelDTO
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/6/13
 **/
@Data
@EqualsAndHashCode
public class ProductExcelDTO {
    /**
     * 产品名称
     */
    private String name;
    /**
     * 创建时间
     */
    private String createDate;
    /**
     * 产品价格
     */
    private String price;
}
