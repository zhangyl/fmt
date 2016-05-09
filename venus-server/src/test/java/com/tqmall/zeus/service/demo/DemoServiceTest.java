package com.tqmall.zeus.service.demo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tqmall.core.common.entity.Result;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-server-context.xml")
@Transactional
public class DemoServiceTest {
	@Autowired
	private DemoService demoService;
	@Test
	public void demoTest() {
		Result<String> r = demoService.sayHello("张三");
		assertTrue("hello, 张三".equals(r.getData()));
	}
}
