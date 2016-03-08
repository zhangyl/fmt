package com.tqmall.wind.manager;

public aspect LogAspect {
	
	pointcut LogPointcut() : execution(* com.tqmall.wind.manager.TireReleaseManager.biz000(..));
	  
	before() : LogPointcut()  {  
	    System.out.println("Hello world");  
	}  
}
