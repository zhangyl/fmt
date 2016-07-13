package com.tqmall.zeus.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tqmall.core.common.entity.Result;

@Controller
@Slf4j
@RequestMapping("/")
public class DemoController {
	@Resource
	com.tqmall.zeus.service.demo.DemoService  demoService;
	
    @RequestMapping("shopList")
    @ResponseBody
    public Result<Boolean> getShopShareList() {
    	Result<String> r = demoService.sayHello("sssss");
    	return Result.wrapSuccessfulResult(r.isSuccess());
    }
    @RequestMapping("param1")
    @ResponseBody
    public Result<Integer> param1(Integer id) {
    	log.info("===================id:" + id);
    	return Result.wrapSuccessfulResult(id);
    }
    @RequestMapping("param2")
    @ResponseBody
    public Result<String> param2(HttpServletRequest request) {
    	String id = request.getParameter("id");
    	log.info("===================id:" + id);
    	return Result.wrapSuccessfulResult(id);
    }
}
