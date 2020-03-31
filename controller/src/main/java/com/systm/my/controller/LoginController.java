package com.systm.my.controller;

import com.system.my.base.utils.Result;
import com.system.my.base.utils.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shitao.liu@luckincoffee.com
 * @date 2020/3/24 11:43
 * @describe
 **/
@RestController
public class LoginController {
    @GetMapping("/login")
    public <T>Result<T> login() {
        return ResultUtil.error(401,"尚未登录，请登录!");
    }
}
