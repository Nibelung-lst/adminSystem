package com.system.my.base.order.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 提交订单入参对象
 * @Author: XieLiuqin  liuqin.xie@luckincoffee.com
 * @Date: 2019/10/26 15:55
 */
public class CreatedOrderDTO implements Serializable {

    /**
     * 订单类型(1:普通订单，2:积分订单)
     */
    @NotNull(message = "订单类型不得为空")
    @Max(value = 2, message = "订单类型错误")
    @Min(value = 1, message = "订单类型错误")
    private Integer type;

    /**
     * 积分商城订单使用积分
     */
    private Integer points;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不得为空")
    private Long userId;

    /**
     * 收货人姓名
     */
    @NotBlank(message = "收货人姓名不能为空")
    private String name;

    /**
     * 收货地址
     */
    @NotBlank(message = "收货地址不能为空")
    private String address;

    /**
     * 收货人电话
     */
    @NotBlank(message = "收货人电话不能为空")
    private String phone;

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
     * 订单详情
     */
    private List<CreatedOrderDetailDTO> orderDetail;

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

    public List<CreatedOrderDetailDTO> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<CreatedOrderDetailDTO> orderDetail) {
        this.orderDetail = orderDetail;
    }

    @Override
    public String toString() {
        return "CreatedOrderDTO{" +
                "type=" + type +
                ", points=" + points +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", couponCode='" + couponCode + '\'' +
                ", couponName='" + couponName + '\'' +
                ", discount=" + discount +
                ", orderDetail=" + orderDetail +
                '}';
    }
}
