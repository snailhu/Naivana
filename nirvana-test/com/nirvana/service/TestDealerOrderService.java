package com.nirvana.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.DealerOrder;
import com.nirvana.repository.dealer.DealerRepository;

@ContextConfiguration(value = { "classpath:applicationContext.xml", "classpath:applicationContext-jpa.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestDealerOrderService {
	@Resource
	private DealerOrderService dealerOrderService;
	@Resource
	private DealerRepository dealerRepository;

	@Test
	public void placeDealerOrder() {
		Map<String, Integer> skus = new HashMap<String, Integer>();
		skus.put("1401", 30);
		skus.put("1221", 200);
		Dealer dealer = dealerRepository.findOne(1l);
		DealerOrder order = dealerOrderService.placeOrder(dealer, skus, false, null);
		System.out.println(order.getCodeInERP());
	}

	@Test
	public void operateNewOrderId() {
		Dealer dealer = dealerRepository.findOne(1l);
		long id = dealerOrderService.operateNewOrderNo(dealer);
		System.out.println(id);
	}

}
