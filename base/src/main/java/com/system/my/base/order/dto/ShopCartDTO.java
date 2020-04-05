package com.system.my.base.order.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description: 加入购物车入参对象
 * @Author: XieLiuqin  liuqin.xie@luckincoffee.com
 * @Date: 2019/10/26 16:14
 */
public class ShopCartDTO implements Serializable {

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Long userId;

    /**
     * sku编号
     */
    @NotBlank(message = "sku编号不能为空")
    private String skuCode;

    /**
     * sku数量
     */
    @NotNull(message = "sku数量不能为空")
    private Integer skuNum;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Integer getSkuNum() {
        return skuNum;
    }

    public void setSkuNum(Integer skuNum) {
        this.skuNum = skuNum;
    }

    @Override
    public String toString() {
        return "ShopCartDTO{" +
                "userId=" + userId +
                ", skuCode='" + skuCode + '\'' +
                ", skuNum=" + skuNum +
                '}';
    }
}
