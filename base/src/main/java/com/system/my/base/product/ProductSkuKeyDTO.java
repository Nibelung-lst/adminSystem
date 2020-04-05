package com.system.my.base.product;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author :Nibelung
 * @Date ：Created in 8:45 2019/10/28
 * @Description :
 * @Modified By :
 * @Version : $
 */
public class ProductSkuKeyDTO implements Serializable {
    private Long id;

    private String spuCode;

    private String name;

    private Long creatorId;

    private String creatorName;

    private List<ProductSkuValueDTO> productSkuValueDTO;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date creatorTime;

    private Long lastModifyId;

    private String lastModifyName;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date lastModifyTime;

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
        this.spuCode = spuCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public List<ProductSkuValueDTO> getProductSkuValueDTO() {
        return productSkuValueDTO;
    }

    public void setProductSkuValueDTO(List<ProductSkuValueDTO> productSkuValueDTO) {
        this.productSkuValueDTO = productSkuValueDTO;
    }

    @Override
    public String toString() {
        return "ProductSkuKeyDTO{" +
                "id=" + id +
                ", spuCode='" + spuCode + '\'' +
                ", name='" + name + '\'' +
                ", creatorId=" + creatorId +
                ", creatorName='" + creatorName + '\'' +
                ", productSkuValueDTO=" + productSkuValueDTO +
                ", creatorTime=" + creatorTime +
                ", lastModifyId=" + lastModifyId +
                ", lastModifyName='" + lastModifyName + '\'' +
                ", lastModifyTime=" + lastModifyTime +
                '}';
    }
}
