package com.system.my.base.product;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author :Nibelung
 * @Date ：Created in 15:45 2019/10/21
 * @Description :
 * @Modified By :
 * @Version : $
 */
public class ProductDTO implements Serializable {
    private String spuCode;

    private String categoryCode;

    private String brandCode;

    private String brandName;

    private String spuName;

    private String spuDescribe;

    private String defaultSkuCode;

    private Boolean status;

    private Long creatorId;

    private String creatorName;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date creatorTime;

    private Long lastModifyId;

    private String lastModifyName;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date lastModifyTime;

    public ProductDTO() {
    }

    public ProductDTO(String spuCode, String defaultSkuCode) {
        this.spuCode = spuCode;
        this.defaultSkuCode = defaultSkuCode;
    }

    public String getSpuCode() {
        return spuCode;
    }

    public void setSpuCode(String spuCode) {
        this.spuCode = spuCode == null ? null : spuCode.trim();
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode == null ? null : categoryCode.trim();
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode == null ? null : brandCode.trim();
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName == null ? null : spuName.trim();
    }

    public String getSpuDescribe() {
        return spuDescribe;
    }

    public void setSpuDescribe(String spuDescribe) {
        this.spuDescribe = spuDescribe == null ? null : spuDescribe.trim();
    }

    public String getDefaultSkuCode() {
        return defaultSkuCode;
    }

    public void setDefaultSkuCode(String defaultSkuCode) {
        this.defaultSkuCode = defaultSkuCode == null ? null : defaultSkuCode.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "ProductDTO{" +
                "spuCode='" + spuCode + '\'' +
                ", categoryCode='" + categoryCode + '\'' +
                ", brandCode='" + brandCode + '\'' +
                ", brandName='" + brandName + '\'' +
                ", spuName='" + spuName + '\'' +
                ", spuDescribe='" + spuDescribe + '\'' +
                ", defaultSkuCode='" + defaultSkuCode + '\'' +
                ", status=" + status +
                ", creatorId=" + creatorId +
                ", creatorName='" + creatorName + '\'' +
                ", creatorTime=" + creatorTime +
                ", lastModifyId=" + lastModifyId +
                ", lastModifyName='" + lastModifyName + '\'' +
                ", lastModifyTime=" + lastModifyTime +
                '}';
    }
}
