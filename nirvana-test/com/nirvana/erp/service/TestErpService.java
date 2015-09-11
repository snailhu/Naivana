package com.nirvana.erp.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nirvana.erp.domain.PepsiOrderHead;
import com.nirvana.erp.domain.PepsiOrderLine;
import com.nirvana.service.SynchronizeService;

@ContextConfiguration(value = { "classpath:applicationContext.xml", "classpath:applicationContext-jpa.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestErpService {

	@Resource
	private ErpService erpService;
	@Resource
	private SynchronizeService synchronizeService;

	@Test
	public void placeOrder() {
		String customerId = "G00132";
		Integer addrNo = 1;
		Map<String, Integer> skus = new HashMap<String, Integer>();
		skus.put("1401", 30);
		skus.put("1221", 200);
		PepsiOrderHead head = erpService.placeOrder(customerId, addrNo, skus);
		Set<PepsiOrderLine> lines = head.getLines();
		System.out.println(lines.size());
		System.out.println(head.getOrderNo());
	}

	@Test
	public void syncMarkets() {
		synchronizeService.syncChannels();
	}

	@Test
	public void syncProducts() {
		synchronizeService.syncProducts();
	}

	@Test
	public void syncCustomers() {
		erpService.syncCustomers(true);
	}

}
