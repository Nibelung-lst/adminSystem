package com.system.my.base.order.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description: 提交订单详情入参对象
 * @Author: XieLiuqin  liuqin.xie@luckincoffee.com
 * @Date: 2019/10/28 09:00
 */
public class CreatedOrderDetailDTO implements Serializable {

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
        return "CreatedOrderDetailDTO{" +
                "skuCode='" + skuCode + '\'' +
                ", skuNum=" + skuNum +
                '}';
    }
}
