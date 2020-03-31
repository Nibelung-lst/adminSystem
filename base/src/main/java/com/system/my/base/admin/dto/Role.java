package com.system.my.base.admin.dto;

import java.io.Serializable;

/**
 * @author shitao.liu@luckincoffee.com
 * @date 2020/3/24 10:51
 * @describe
 **/
public class Role implements Serializable {
    private Integer id;

    private String name;

    private String nameZh;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }
}
