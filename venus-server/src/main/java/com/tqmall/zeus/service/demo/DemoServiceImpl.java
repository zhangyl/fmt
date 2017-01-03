package com.tqmall.zeus.service.demo;

import org.springframework.beans.factory.annotation.Autowired;

import com.tqmall.zeus.manager.demo.DemoManager;
import com.tqmall.zeus.service.Result;

public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoManager demoManager;

    @Override
    public Result<String> sayHello(String name) {
        if (name == null) {
            throw new RuntimeException("name参数不能为空");
        }
        String resultData = demoManager.sayHello(name);
        return new Result<String>(resultData);
    }

}
