package com.nirvana.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nirvana.domain.store.Store;

@ContextConfiguration(value = { "classpath:applicationContext.xml", "classpath:applicationContext-jpa.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestPersonnelManageService {
	@Resource
	private AgentManageService agentManageService;

	@Test
	public void getNotInThisRouteStores() {
		Page<Store> page = agentManageService.getNotInThisRouteStores(1, 1, 1, 10);
		System.out.println(page.getSize());
	}

}
