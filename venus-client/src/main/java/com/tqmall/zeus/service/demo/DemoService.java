package com.tqmall.zeus.service.demo;

import com.tqmall.zeus.service.Result;

public interface DemoService {
    /**
     * 打招呼
     * @param name
     * @return
     */
    Result<String> sayHello(String name);

}
