package com.system.my.base.product;

import java.io.Serializable;

/**
 * @Author :Nibelung
 * @Date ：Created in 19:05 2019/11/4
 * @Description :
 */
public class CategoryParentVO implements Serializable {
    private String categoryCode;

    private String name;

    private Boolean status;

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
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CategoryParentVO{" +
                "categoryCode='" + categoryCode + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
