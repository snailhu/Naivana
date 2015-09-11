package com.nirvana.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.repository.dealer.DealerRepository;
import com.nirvana.repository.store.StoreRepository;
import com.nirvana.repository.store.usersys.StoreUserRepository;

@ContextConfiguration(value = { "classpath:applicationContext.xml", "classpath:applicationContext-jpa.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestStoreAccountService {
	@Resource
	private StoreOrderService storeOrderService;
	@Resource
	private StoreUserRepository storeUserRepository;
	@Resource
	private StoreRepository storeRepository;
	@Resource
	private DealerRepository dealerRepository;

	@Test
	public void operateNewOrderId() {
		Dealer dealer = dealerRepository.findOne(1l);
		long id = storeOrderService.operateNewOrderNo(dealer);
		System.out.println(id);
	}

}
