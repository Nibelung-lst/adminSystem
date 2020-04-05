package com.system.my.base.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 返回PRC的订单实体类对象
 * @Author: XieLiuqin  liuqin.xie@luckincoffee.com
 * @Date: 2019/10/26 14:55
 */
public class OrderVO implements Serializable {

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 订单类型(1:普通订单，2:积分订单)
     */
    private Integer type;

    /**
     * 积分商城订单使用积分
     */
    private Integer points;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 收货人姓名
     */
    private String name;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 收货人电话
     */
    private String phone;

    /**
     * 订单原价
     */
    private BigDecimal originalPrice;

    /**
     * 优惠券编号
     */
    private String couponCode;

    /**
     * 优惠券名
     */
    private String couponName;

    /**
     * 会员折扣
     */
    private BigDecimal discount;

    /**
     * 实付金额
     */
    private BigDecimal truthPrice;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 取消时间
     */
    private String cancelTime;

    /**
     * 付款时间
     */
    private String payTime;

    /**
     * 发货时间
     */
    private String deliverTime;

    /**
     * 成交时间
     */
    private String finishTime;

    /**
     * 取消原因
     */
    private String cancelReason;

    /**
     * 订单状态（-1:已取消 1：待付款 2：待发货 3：待收货 4：待评价 5：已成交）
     */
    private Integer status;

    /**
     * 订单详情
     */
    private List<OrderDetailVO> orderDetailVOList;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTruthPrice() {
        return truthPrice;
    }

    public void setTruthPrice(BigDecimal truthPrice) {
        this.truthPrice = truthPrice;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(String deliverTime) {
        this.deliverTime = deliverTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<OrderDetailVO> getOrderDetailVOList() {
        return orderDetailVOList;
    }

    public void setOrderDetailVOList(List<OrderDetailVO> orderDetailVOList) {
        this.orderDetailVOList = orderDetailVOList;
    }

    @Override
    public String toString() {
        return "OrderVO{" +
                "orderCode='" + orderCode + '\'' +
                ", type=" + type +
                ", points=" + points +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", originalPrice=" + originalPrice +
                ", couponCode='" + couponCode + '\'' +
                ", couponName='" + couponName + '\'' +
                ", discount=" + discount +
                ", truthPrice=" + truthPrice +
                ", createTime='" + createTime + '\'' +
                ", cancelTime='" + cancelTime + '\'' +
                ", payTime='" + payTime + '\'' +
                ", deliverTime='" + deliverTime + '\'' +
                ", finishTime='" + finishTime + '\'' +
                ", cancelReason='" + cancelReason + '\'' +
                ", status=" + status +
                ", orderDetailVOList=" + orderDetailVOList +
                '}';
    }
}
