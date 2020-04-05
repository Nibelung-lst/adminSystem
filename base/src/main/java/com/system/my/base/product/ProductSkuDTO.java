package com.system.my.base.product;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author :Nibelung
 * @Date ：Created in 8:44 2019/10/28
 * @Description :
 * @Modified By :
 * @Version : $
 */
public class ProductSkuDTO implements Serializable {
    private Long id;

    private String spuCode;

    private String skuCode;

    private String uniqueCode;

    private String name;

    private String describe;

    private Boolean status;

    private Boolean defaultMarkup;

    private BigDecimal originPrice;

    private BigDecimal currentPrice;

    private String image;

    private Long creatorId;

    private String creatorName;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date creatorTime;

    private Long lastModifyId;

    private String lastModifyName;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date lastModifyTime;

    public Boolean getDefaultMarkup() {
        return defaultMarkup;
    }

    public void setDefaultMarkup(Boolean defaultMarkup) {
        this.defaultMarkup = defaultMarkup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpuCode() {
        return spuCode;
    }

    public void setSpuCode(String spuCode) {
        this.spuCode = spuCode == null ? null : spuCode.trim();
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode == null ? null : uniqueCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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
        this.image = image == null ? null : image.trim();
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
    }

    public Date getCreatorTime() {
        return creatorTime;
    }

    public void setCreatorTime(Date creatorTime) {
        this.creatorTime = creatorTime;
    }

    public Long getLastModifyId() {
        return lastModifyId;
    }

    public void setLastModifyId(Long lastModifyId) {
        this.lastModifyId = lastModifyId;
    }

    public String getLastModifyName() {
        return lastModifyName;
    }

    public void setLastModifyName(String lastModifyName) {
        this.lastModifyName = lastModifyName == null ? null : lastModifyName.trim();
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    @Override
    public String toString() {
        return "ProductSkuDTO{" +
                "id=" + id +
                ", spuCode='" + spuCode + '\'' +
                ", skuCode='" + skuCode + '\'' +
                ", uniqueCode='" + uniqueCode + '\'' +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", status=" + status +
                ", defaultMarkup=" + defaultMarkup +
                ", originPrice=" + originPrice +
                ", currentPrice=" + currentPrice +
                ", image='" + image + '\'' +
                ", creatorId=" + creatorId +
                ", creatorName='" + creatorName + '\'' +
                ", creatorTime=" + creatorTime +
                ", lastModifyId=" + lastModifyId +
                ", lastModifyName='" + lastModifyName + '\'' +
                ", lastModifyTime=" + lastModifyTime +
                '}';
    }
}
