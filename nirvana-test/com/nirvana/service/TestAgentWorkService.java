package com.nirvana.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nirvana.domain.backend.VisitNodeType;

@ContextConfiguration(value = { "classpath:applicationContext.xml", "classpath:applicationContext-jpa.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestAgentWorkService {

	@Resource
	private AgentWorkService agentWorkService;

	@Test
	public void placeOrder() {
		Map<String, Integer> orderItems = new HashMap<String, Integer>();
		orderItems.put("1201", 2);
		agentWorkService.placeOrder(VisitNodeType.DISTRIBUTE_STORE, 1, orderItems, null);
	}
}
