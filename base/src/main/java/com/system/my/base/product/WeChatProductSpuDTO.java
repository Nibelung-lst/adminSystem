package com.system.my.base.product;

import java.math.BigDecimal;

/**
 * @Author :Nibelung
 * @Date ：Created in 16:34 2019/11/9
 * @Description :
 */
public class WeChatProductSpuDTO {
    private String spuCode;

    private String categoryCode;

    private String brandCode;

    private String brandName;

    private String spuName;

    private String spuDescribe;

    private String defaultImage;

    private BigDecimal defaultOriginPrice;

    private BigDecimal defaultCurrentPrice;

    public String getSpuCode() {
        return spuCode;
    }

    public void setSpuCode(String spuCode) {
        this.spuCode = spuCode;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public String getSpuDescribe() {
        return spuDescribe;
    }

    public void setSpuDescribe(String spuDescribe) {
        this.spuDescribe = spuDescribe;
    }

    public String getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
    }

    public BigDecimal getDefaultOriginPrice() {
        return defaultOriginPrice;
    }

    public void setDefaultOriginPrice(BigDecimal defaultOriginPrice) {
        this.defaultOriginPrice = defaultOriginPrice;
    }

    public BigDecimal getDefaultCurrentPrice() {
        return defaultCurrentPrice;
    }

    public void setDefaultCurrentPrice(BigDecimal defaultCurrentPrice) {
        this.defaultCurrentPrice = defaultCurrentPrice;
    }

    @Override
    public String toString() {
        return "WeChatProductSpuDTO{" +
                "spuCode='" + spuCode + '\'' +
                ", categoryCode='" + categoryCode + '\'' +
                ", brandCode='" + brandCode + '\'' +
                ", brandName='" + brandName + '\'' +
                ", spuName='" + spuName + '\'' +
                ", spuDescribe='" + spuDescribe + '\'' +
                ", defaultImage='" + defaultImage + '\'' +
                ", defaultOriginPrice=" + defaultOriginPrice +
                ", defaultCurrentPrice=" + defaultCurrentPrice +
                '}';
    }
}
