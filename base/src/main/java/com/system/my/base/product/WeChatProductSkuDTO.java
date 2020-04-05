package com.system.my.base.product;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author :Nibelung
 * @Date ：Created in 9:19 2019/11/12
 * @Description :
 */
public class WeChatProductSkuDTO implements Serializable {
    private String skuCode;

    private String skuName;

    private Boolean status;

    private String describe;

    private BigDecimal originPrice;

    private BigDecimal currentPrice;

    private String image;

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public BigDecimal getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(BigDecimal originPrice) {
        this.originPrice = originPrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "WeChatProductSkuDTO{" +
                "skuCode='" + skuCode + '\'' +
                ", skuName='" + skuName + '\'' +
                ", status=" + status +
                ", describe='" + describe + '\'' +
                ", originPrice=" + originPrice +
                ", currentPrice=" + currentPrice +
                ", image='" + image + '\'' +
                '}';
    }
}
