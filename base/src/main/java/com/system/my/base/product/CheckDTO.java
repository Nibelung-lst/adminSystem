package com.system.my.base.product;

import java.io.Serializable;

/**
 * @Author :Nibelung
 * @Date ：Created in 11:08 2019/11/9
 * @Description :
 * @Modified By :
 * @Version : $
 */
public class CheckDTO implements Serializable {
    private String name;

    private String condition;

    private Long key;

    public CheckDTO(String name, String condition) {
        this.name = name;
        this.condition = condition;
    }

    public CheckDTO(String name, Long key) {
        this.name = name;
        this.key = key;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "CheckDTO{" +
                "name='" + name + '\'' +
                ", condition='" + condition + '\'' +
                ", key=" + key +
                '}';
    }
}
