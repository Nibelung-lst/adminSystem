package com.system.my.base.product;

import java.io.Serializable;

/**
 * @Author :Nibelung
 * @Date ：Created in 17:54 2019/11/4
 * @Description :
 * @Modified By :
 * @Version : $
 */
public class SkuCode implements Serializable {
    private String skuCode;

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    @Override
    public String toString() {
        return "SkuCode{" +
                "skuCode='" + skuCode + '\'' +
                '}';
    }
}
