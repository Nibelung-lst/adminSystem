package com.system.my.base;

import java.io.Serializable;

/**
 * @author shitao.liu@luckincoffee.com
 * @date 2020/3/22 16:30
 * @describe
 **/
public class test implements Serializable {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) {
        String a = "f4e2e52034348f86b67cde581c0f9eb5";
        String b = "1GjGKukS6C5IPs3D53MhjPzNxI8SWkVs";
        System.out.println(a.getBytes());
    }
}
