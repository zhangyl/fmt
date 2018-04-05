package com.tqmall.zeus.service.init;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class AfterBeanInit implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {// root application context 没有parent，他就是老大.
			// 需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
			System.out.println("~~~~~~~~~~ok~~~~~");
		} else {
			System.out.println("~~~~~~~~~~ok2~~~~~");
		}

	}

}
