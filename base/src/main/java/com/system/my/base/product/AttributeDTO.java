package com.system.my.base.product;

/**
 * @author shitao.liu@luckincoffee.com
 * @date 2020/4/4 15:57
 * @describe
 **/
public class AttributeDTO {
    private Integer typeId;
    private String typeName;
    private Integer attributeId;
    private String attributeName;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
}
