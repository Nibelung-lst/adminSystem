package com.system.my.base.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description: t_order_detail表的实体类对象
 * @Author: XieLiuqin  liuqin.xie@luckincoffee.com
 * @Date: 2019/10/25 22:56
 */
public class OrderDetailDO implements Serializable {

    /**
     * 订单详情id
     */
    private Long id;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * spu编号
     */
    private String spuCode;

    /**
     * spu名称
     */
    private String spuName;

    /**
     * sku编号
     */
    private String skuCode;

    /**
     * sku名称
     */
    private String skuName;

    /**
     * sku属性
     */
    private String specs;

    /**
     * sku数量
     */
    private Integer skuNum;

    /**
     * sku图片
     */
    private String skuImage;

    /**
     * sku原价
     */
    private BigDecimal originPrice;

    /**
     * sku现价
     */
    private BigDecimal currentPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getSpuCode() {
        return spuCode;
    }

    public void setSpuCode(String spuCode) {
        this.spuCode = spuCode;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public Integer getSkuNum() {
        return skuNum;
    }

    public void setSkuNum(Integer skuNum) {
        this.skuNum = skuNum;
    }

    public String getSkuImage() {
        return skuImage;
    }

    public void setSkuImage(String skuImage) {
        this.skuImage = skuImage;
    }

    public BigDecimal getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(BigDecimal originPrice) {
        this.originPrice = originPrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public String toString() {
        return "OrderDetailDO{" +
                "id=" + id +
                ", orderCode='" + orderCode + '\'' +
                ", spuCode='" + spuCode + '\'' +
                ", spuName='" + spuName + '\'' +
                ", skuCode='" + skuCode + '\'' +
                ", skuName='" + skuName + '\'' +
                ", specs='" + specs + '\'' +
                ", skuNum=" + skuNum +
                ", skuImage='" + skuImage + '\'' +
                ", originPrice=" + originPrice +
                ", currentPrice=" + currentPrice +
                '}';
    }
}
