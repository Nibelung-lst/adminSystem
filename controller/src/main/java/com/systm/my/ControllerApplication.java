package com.systm.my;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author shitao.liu@luckincoffee.com
 * @date 2020/3/22 15:12
 * @describe
 **/
@SpringBootApplication(scanBasePackages="com")
@EnableScheduling
@EnableCaching
@MapperScan(basePackages = "com.system.my.mapper")
public class ControllerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ControllerApplication.class, args);
    }
}
