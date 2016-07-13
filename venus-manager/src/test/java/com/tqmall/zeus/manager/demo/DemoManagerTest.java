package com.tqmall.zeus.manager.demo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-biz-context.xml")
@Transactional
public class DemoManagerTest {
	@Autowired	
	private DemoManager demoManager;
	@Test
	public void demoTest() {
		String r = demoManager.sayHello("lisi");
		assertTrue("hello, lisi".equals(r));
	}
}
