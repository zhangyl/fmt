package com.tqmall.zeus.manager.demo;

import org.springframework.stereotype.Service;

@Service
public class DemoManager {
	public String sayHello(String name) {
		return "hello, " + name;
	}
}
