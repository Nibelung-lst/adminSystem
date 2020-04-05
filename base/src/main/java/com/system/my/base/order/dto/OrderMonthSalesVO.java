package com.system.my.base.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description: 返回RPC的订单月销量统计结果
 * @Author: XieLiuqin  liuqin.xie@luckincoffee.com
 * @Date: 2019/11/9 16:47
 */
public class OrderMonthSalesVO implements Serializable {

    /**
     * 月份
     */
    private Integer month;

    /**
     * 销量
     */
    private Integer count;

    /**
     * 销售额
     */
    private BigDecimal sale;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getSale() {
        return sale;
    }

    public void setSale(BigDecimal sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "OrderMonthSalesVO{" +
                "month=" + month +
                ", count=" + count +
                ", sale=" + sale +
                '}';
    }
}
