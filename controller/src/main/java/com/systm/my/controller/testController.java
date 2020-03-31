package com.systm.my.controller;

import com.system.my.service.testService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shitao.liu@luckincoffee.com
 * @date 2020/3/22 15:13
 * @describe
 **/
@RestController
@RequestMapping("demo")
public class testController {
    @Autowired
    private testService testService;

    @GetMapping("test")
    public String test(){
        return testService.test();
    }
}
