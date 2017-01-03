package com.tqmall.zeus.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tqmall.zeus.service.Result;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/")
public class DemoController {
    private static final Logger              log = LoggerFactory.getLogger(DemoController.class);
    @Resource
    com.tqmall.zeus.service.demo.DemoService demoService;

    @RequestMapping("shopList")
    @ResponseBody
    public Result<Boolean> getShopShareList() {
        Result<String> r = demoService.sayHello("sssss");
        return new Result<Boolean>(r.getSuccess());
    }

    @RequestMapping("param1")
    @ResponseBody
    public Result<Integer> param1(Integer id) {
        log.info("===================id:" + id);
        return new Result();
    }

    @RequestMapping("param2")
    @ResponseBody
    public Result<String> param2(HttpServletRequest request) {
        String id = request.getParameter("id");
        log.info("===================id:" + id);
        return new Result();
    }
}
