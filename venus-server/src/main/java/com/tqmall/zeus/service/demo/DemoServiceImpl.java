package com.tqmall.zeus.service.demo;

import org.springframework.beans.factory.annotation.Autowired;

import com.tqmall.core.common.entity.Result;
import com.tqmall.core.common.exception.BusinessCheckFailException;
import com.tqmall.zeus.manager.demo.DemoManager;

public class DemoServiceImpl implements DemoService {
	
	@Autowired
	private DemoManager demoManager;
	
	@Override
	public Result<String> sayHello(String name) {
		if(name == null) {
			throw new BusinessCheckFailException(null, "name参数不能为空");
		}
		String resultData = demoManager.sayHello(name);
		return Result.wrapSuccessfulResult(resultData);
	}

}
