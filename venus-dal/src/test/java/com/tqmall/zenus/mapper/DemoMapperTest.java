package com.tqmall.zenus.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tqmall.zeus.mapper.DemoMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-dao-context.xml")
@Transactional
public class DemoMapperTest {
	@Autowired
	DemoMapper demoMapper;
	
	@Test
	public void getShopShareList() {
		System.out.println("OK");
	}
	
}
