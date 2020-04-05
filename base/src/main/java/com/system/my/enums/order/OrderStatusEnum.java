package com.system.my.enums.order;

/**
 * @Description: 订单状态枚举类
 * @Author: XieLiuqin  liuqin.xie@luckincoffee.com
 * @Date: 2019/11/5 11:10
 */
public enum OrderStatusEnum {

    /**
     * 待付款状态
     */
    NOT_PAY_STATUS(1, "待付款状态"),

    /**
     * 待发货状态
     */
    NOT_DELIVER_STATUS(2, "待发货状态"),

    /**
     * 待收货状态
     */
    NOT_RECEIVE_STATUS(3, "待收货状态"),

    /**
     * 待评价状态
     */
    NOT_EVALUATE_STATUS(4, "待评价状态"),

    /**
     * 已完成状态
     */
    FINISH_STATUS(5 ,"已完成状态");

    /**
     * 状态
     */
    private Integer status;

    /**
     * 描述
     */
    private String describe;

    OrderStatusEnum(Integer status, String describe) {
        this.status = status;
        this.describe = describe;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
