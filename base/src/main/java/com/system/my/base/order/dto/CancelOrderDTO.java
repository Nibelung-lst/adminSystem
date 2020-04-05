package com.system.my.base.order.dto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Description: 取消订单入参对象
 * @Author: XieLiuqin  liuqin.xie@luckincoffee.com
 * @Date: 2019/10/26 10:30
 */
public class CancelOrderDTO implements Serializable {

    /**
     * 订单编号
     */
    @NotBlank(message = "订单编号不能为空")
    private String orderCode;

    /**
     * 取消原因
     */
    @NotBlank(message = "取消原因不能为空")
    private String cancelReason;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    @Override
    public String toString() {
        return "CancelOrderDTO{" +
                "orderCode='" + orderCode + '\'' +
                ", cancelReason='" + cancelReason + '\'' +
                '}';
    }
}
