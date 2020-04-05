package com.system.my.base.order.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description: 订单失败入参对象
 * @Author: XieLiuqin  liuqin.xie@luckincoffee.com
 * @Date: 2019/11/9 10:23
 */
public class OrderFailDTO implements Serializable {

    /**
     * 订单号
     */
    @NotBlank(message = "订单编号不能为空")
    private String orderCode;

    /**
     * 订单类型(1:普通订单，2:积分商城订单)
     */
    @NotNull(message = "订单类型不得为空")
    @Max(value = 2, message = "订单类型错误")
    @Min(value = 1, message = "订单类型错误")
    private Integer orderType;

    /**
     * 失败类型
     */
    @NotNull(message = "订单失败类型不能为空")
    private Integer failType;

    /**
     * 失败描述
     */
    @NotBlank(message = "订单失败描述不能为空")
    private String desc;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getFailType() {
        return failType;
    }

    public void setFailType(Integer failType) {
        this.failType = failType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "OrderFailDTO{" +
                "orderCode='" + orderCode + '\'' +
                ", orderType=" + orderType +
                ", failType=" + failType +
                ", desc='" + desc + '\'' +
                '}';
    }
}
