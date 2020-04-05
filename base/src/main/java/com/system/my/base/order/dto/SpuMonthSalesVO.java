package com.system.my.base.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description: 返回RPC的商品月销量统计结果
 * @Author: XieLiuqin  liuqin.xie@luckincoffee.com
 * @Date: 2019/11/11 11:23
 */
public class SpuMonthSalesVO implements Serializable {

    /**
     * 商品名称
     */
    private String spuName;

    /**
     * 销量
     */
    private Integer spuCount;

    /**
     * 销售额
     */
    private BigDecimal spuSale;

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public Integer getSpuCount() {
        return spuCount;
    }

    public void setSpuCount(Integer spuCount) {
        this.spuCount = spuCount;
    }

    public BigDecimal getSpuSale() {
        return spuSale;
    }

    public void setSpuSale(BigDecimal spuSale) {
        this.spuSale = spuSale;
    }

    @Override
    public String toString() {
        return "SpuMonthSalesVO{" +
                "spuName='" + spuName + '\'' +
                ", spuCount=" + spuCount +
                ", spuSale=" + spuSale +
                '}';
    }
}
