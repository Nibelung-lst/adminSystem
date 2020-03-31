package com.system.my.serviceimpl;

import com.system.my.base.test;
import com.system.my.mapper.TestMapper;
import com.system.my.service.testService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author shitao.liu@luckincoffee.com
 * @date 2020/3/22 15:44
 * @describe
 **/
@Service
public class testServiceImpl implements testService {
    @Resource
    TestMapper mapper;

    @Override
    public String test() {
        List<test> testList = mapper.select();
        String a = "";
        test t = testList.get(0);
        a = t.getName()+t.getPassword();
        return a;
    }
}
