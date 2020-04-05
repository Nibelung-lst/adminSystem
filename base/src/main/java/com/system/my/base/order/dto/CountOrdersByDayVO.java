package com.system.my.base.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description: 返回RPC的按日期统计订单结果
 * @Author: XieLiuqin  liuqin.xie@luckincoffee.com
 * @Date: 2019/11/5 13:16
 */
public class CountOrdersByDayVO implements Serializable {

    /**
     * 今日订单总数
     */
    private Integer todayOrderNum;

    /**
     * 今日销售总额
     */
    private BigDecimal todayOrderAmount;

    /**
     * 昨日销售总额
     */
    private BigDecimal yesterdayOrderAmount;

    public Integer getTodayOrderNum() {
        return todayOrderNum;
    }

    public void setTodayOrderNum(Integer todayOrderNum) {
        this.todayOrderNum = todayOrderNum;
    }

    public BigDecimal getTodayOrderAmount() {
        return todayOrderAmount;
    }

    public void setTodayOrderAmount(BigDecimal todayOrderAmount) {
        this.todayOrderAmount = todayOrderAmount;
    }

    public BigDecimal getYesterdayOrderAmount() {
        return yesterdayOrderAmount;
    }

    public void setYesterdayOrderAmount(BigDecimal yesterdayOrderAmount) {
        this.yesterdayOrderAmount = yesterdayOrderAmount;
    }

    @Override
    public String toString() {
        return "CountOrdersByDayVO{" +
                "todayOrderNum=" + todayOrderNum +
                ", todayOrderAmount=" + todayOrderAmount +
                ", yesterdayOrderAmount=" + yesterdayOrderAmount +
                '}';
    }
}
