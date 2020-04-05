package com.system.my.base.product;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author :Nibelung
 * @Date ：Created in 9:53 2019/10/26
 * @Description :
 * @Modified By :
 * @Version : $
 */
public class ProductCategoryDTO implements Serializable {
    private String iconImage;

    private String parentCode;

    private String categoryCode;

    private String image;

    private String name;

    private Boolean status;

    private Long creatorId;

    private String creatorName;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date creatorTime;

    private Long lastModifyId;

    private String lastModifyName;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date lastModifyTime;

    public String getIconImage() {
        return iconImage;
    }

    public void setIconImage(String iconImage) {
        this.iconImage = iconImage;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
        return "ProductCategoryDTO{" +
                "iconImage='" + iconImage + '\'' +
                ", parentCode='" + parentCode + '\'' +
                ", categoryCode='" + categoryCode + '\'' +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
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
