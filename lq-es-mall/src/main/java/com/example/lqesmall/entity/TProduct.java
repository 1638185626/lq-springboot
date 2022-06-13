package com.example.lqesmall.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

/**
 * (TProduct)表实体类
 *
 * @author liuqing
 * @since 2022-05-20 23:15:04
 */
@SuppressWarnings("serial")
@TableName("t_product")
public class TProduct extends Model<TProduct> {
    //商品id
    private Long id;
    //商品名称
    private String title;
    //商品价格
    private Double price;
    //库存数量
    private Integer inveCnt;
    //浏览量
    private Long pv;
    //商品折扣
    private Double discount;
    //商品图片
    private String pImg;
    //商品图片
    private String pImg2;
    //商品图片
    private String pImg3;
    
    private Date createTime;
    
    private Date updateTime;
    
    private Long createUserId;
    
    private Long updateUserId;
    
    private Integer deleted;
    //乐观锁
    private Long version;
    //商家Id
    private Long bId;
    //销量
    private Long salesCnt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getInveCnt() {
        return inveCnt;
    }

    public void setInveCnt(Integer inveCnt) {
        this.inveCnt = inveCnt;
    }

    public Long getPv() {
        return pv;
    }

    public void setPv(Long pv) {
        this.pv = pv;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getPImg() {
        return pImg;
    }

    public void setPImg(String pImg) {
        this.pImg = pImg;
    }

    public String getPImg2() {
        return pImg2;
    }

    public void setPImg2(String pImg2) {
        this.pImg2 = pImg2;
    }

    public String getPImg3() {
        return pImg3;
    }

    public void setPImg3(String pImg3) {
        this.pImg3 = pImg3;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getBId() {
        return bId;
    }

    public void setBId(Long bId) {
        this.bId = bId;
    }

    public Long getSalesCnt() {
        return salesCnt;
    }

    public void setSalesCnt(Long salesCnt) {
        this.salesCnt = salesCnt;
    }


    }

