package com.system.my.base.product;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author :Nibelung
 * @Date ：Created in 9:42 2019/11/9
 * @Description :
 * @Modified By :
 * @Version : $
 */
public class ProductDetailDTO implements Serializable {
    private String spuCode;

    private String spuName;

    private String spuImage;

    private String spuDescribe;

    private Boolean spuStatus;

    private BigDecimal originPrice;

    private BigDecimal currentPrice;

    public String getSpuCode() {
        return spuCode;
    }

    public void setSpuCode(String spuCode) {
        this.spuCode = spuCode;
    }

    public Boolean getSpuStatus() {
        return spuStatus;
    }

    public void setSpuStatus(Boolean spuStatus) {
        this.spuStatus = spuStatus;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public String getSpuImage() {
        return spuImage;
    }

    public void setSpuImage(String spuImage) {
        this.spuImage = spuImage;
    }

    public String getSpuDescribe() {
        return spuDescribe;
    }

    public void setSpuDescribe(String spuDescribe) {
        this.spuDescribe = spuDescribe;
    }

    public boolean isSpuStatus() {
        return spuStatus;
    }

    public void setSpuStatus(boolean spuStatus) {
        this.spuStatus = spuStatus;
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

    @Override
    public String toString() {
        return "ProductDetailDTO{" +
                "spuCode='" + spuCode + '\'' +
                ", spuName='" + spuName + '\'' +
                ", spuImage='" + spuImage + '\'' +
                ", spuDescribe='" + spuDescribe + '\'' +
                ", spuStatus=" + spuStatus +
                ", originPrice=" + originPrice +
                ", currentPrice=" + currentPrice +
                '}';
    }
}
