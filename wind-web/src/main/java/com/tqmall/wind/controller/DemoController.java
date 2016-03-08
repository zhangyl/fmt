package com.tqmall.wind.controller;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tqmall.core.common.entity.Result;
import com.tqmall.wind.manager.ProxyManager;
import com.tqmall.wind.manager.TireReleaseManager;

/**
 * 区域接口
 * Created by zxd on 15/10/29.
 */
@Controller
@RequestMapping(value = "/")
public class DemoController {
	@Resource
	TireReleaseManager tireReleaseManager;
	@Resource
	ProxyManager proxyManager;

    @RequestMapping(value = "add", produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    @ResponseBody
    public Result<String> add() {
//    	Thread t1 = new Thread(new Runnable(){
//
//			@Override
//			public void run() {
//				tireReleaseManager.biz(21);
//			}}, "t1");
//    	Thread t2 = new Thread(new Runnable(){
//    		
//    		@Override
//    		public void run() {
//    			tireReleaseManager.biz(21);
//    		}}, "t2");
//    	t1.start();
//    	try {
//			Thread.sleep(1000L);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//    	t2.start();
    	tireReleaseManager.biz(21);
    	//proxyManager.biz();
        return Result.wrapSuccessfulResult("");
    }
}
