package com.system.my.base.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: t_order表的实体类对象
 * @Author: XieLiuqin  liuqin.xie@luckincoffee.com
 * @Date: 2019/10/25 09:42
 */
public class OrderDO implements Serializable {

    /**
     * 订单id
     */
    private Long id;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 订单类型(1:普通订单，2:积分商城订单，3:秒杀订单)
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
    private Date createTime;

    /**
     * 取消时间
     */
    private Date cancelTime;

    /**
     * 付款时间
     */
    private Date payTime;

    /**
     * 发货时间
     */
    private Date deliverTime;

    /**
     * 成交时间
     */
    private Date finishTime;

    /**
     * 取消原因
     */
    private String cancelReason;

    /**
     * 订单状态（-1:已取消 1：待付款 2：待发货 3：待收货 4：待评价 5：已成交）
     */
    private Integer status;

    /**
     * 获取订单id
     *
     * @return id - 订单id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置订单id
     *
     * @param id 订单id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取订单编号
     *
     * @return order_code - 订单编号
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 设置订单编号
     *
     * @param orderCode 订单编号
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    /**
     * 获取订单类型(1:普通订单，2:积分商城订单，3:秒杀订单)
     *
     * @return type - 订单类型(1:普通订单，2:积分商城订单，3:秒杀订单)
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置订单类型(1:普通订单，2:积分商城订单，3:秒杀订单)
     *
     * @param type 订单类型(1:普通订单，2:积分商城订单，3:秒杀订单)
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取积分商城订单使用积分
     *
     * @return points - 积分商城订单使用积分
     */
    public Integer getPoints() {
        return points;
    }

    /**
     * 设置积分商城订单使用积分
     *
     * @param points 积分商城订单使用积分
     */
    public void setPoints(Integer points) {
        this.points = points;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取收货人姓名
     *
     * @return name - 收货人姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置收货人姓名
     *
     * @param name 收货人姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取收货地址
     *
     * @return address - 收货地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置收货地址
     *
     * @param address 收货地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取收货人电话
     *
     * @return phone - 收货人电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置收货人电话
     *
     * @param phone 收货人电话
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取订单原价
     *
     * @return original_price - 订单原价
     */
    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    /**
     * 设置订单原价
     *
     * @param originalPrice 订单原价
     */
    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    /**
     * 获取优惠券编号
     *
     * @return coupon_code - 优惠券编号
     */
    public String getCouponCode() {
        return couponCode;
    }

    /**
     * 设置优惠券编号
     *
     * @param couponCode 优惠券编号
     */
    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode == null ? null : couponCode.trim();
    }

    /**
     * 获取优惠券名
     *
     * @return coupon_name - 优惠券名
     */
    public String getCouponName() {
        return couponName;
    }

    /**
     * 设置优惠券名
     *
     * @param couponName 优惠券名
     */
    public void setCouponName(String couponName) {
        this.couponName = couponName == null ? null : couponName.trim();
    }

    /**
     * 获取会员折扣
     *
     * @return discount - 会员折扣
     */
    public BigDecimal getDiscount() {
        return discount;
    }

    /**
     * 设置会员折扣
     *
     * @param discount 会员折扣
     */
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    /**
     * 获取实付金额
     *
     * @return truth_price - 实付金额
     */
    public BigDecimal getTruthPrice() {
        return truthPrice;
    }

    /**
     * 设置实付金额
     *
     * @param truthPrice 实付金额
     */
    public void setTruthPrice(BigDecimal truthPrice) {
        this.truthPrice = truthPrice;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取取消时间
     *
     * @return cancel_time - 取消时间
     */
    public Date getCancelTime() {
        return cancelTime;
    }

    /**
     * 设置取消时间
     *
     * @param cancelTime 取消时间
     */
    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    /**
     * 获取付款时间
     *
     * @return pay_time - 付款时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 设置付款时间
     *
     * @param payTime 付款时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取发货时间
     *
     * @return deliver_time - 发货时间
     */
    public Date getDeliverTime() {
        return deliverTime;
    }

    /**
     * 设置发货时间
     *
     * @param deliverTime 发货时间
     */
    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    /**
     * 获取成交时间
     *
     * @return finish_time - 成交时间
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * 设置成交时间
     *
     * @param finishTime 成交时间
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * 获取取消原因
     *
     * @return cancel_reason - 取消原因
     */
    public String getCancelReason() {
        return cancelReason;
    }

    /**
     * 设置取消原因
     *
     * @param cancelReason 取消原因
     */
    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason == null ? null : cancelReason.trim();
    }

    /**
     * 获取订单状态（-1:已取消 1：待付款 2：待发货 3：待收货 4：待评价 5：已成交）
     *
     * @return status - 订单状态（-1:已取消 1：待付款 2：待发货 3：待收货 4：待评价 5：已成交）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置订单状态（-1:已取消 1：待付款 2：待发货 3：待收货 4：待评价 5：已成交）
     *
     * @param status 订单状态（-1:已取消 1：待付款 2：待发货 3：待收货 4：待评价 5：已成交）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderDO{" +
                "id=" + id +
                ", orderCode='" + orderCode + '\'' +
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
                ", createTime=" + createTime +
                ", cancelTime=" + cancelTime +
                ", payTime=" + payTime +
                ", deliverTime=" + deliverTime +
                ", finishTime=" + finishTime +
                ", cancelReason='" + cancelReason + '\'' +
                ", status=" + status +
                '}';
    }
}
