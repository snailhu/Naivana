package com.nirvana.repository;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nirvana.domain.dealer.Dealer;
import com.nirvana.repository.dealer.DealerRepository;

@ContextConfiguration(value = { "classpath:applicationContext-jpa.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestDealerRepository {

	@Resource
	private DealerRepository dealerRepository;

	@Test
	public void findByAgentAreaIdAndNotInVisitRouteId() {
		long time = System.currentTimeMillis();
		List<Dealer> list = dealerRepository.findInVisitRouteDealers(4L);
		for (Dealer dealer : list) {
			System.out.println(dealer.getId());
		}
		Page<Dealer> page;
		if (list.size() == 0) {
			page = dealerRepository.findByAgentAreaId(1, new PageRequest(0, 100));
		} else {
			page = dealerRepository.findByAgentAreaIdAndNotInList(1, list, new PageRequest(0, 100));
		}
		for (Dealer dealer : page) {
			System.out.println(dealer.getStorefrontInfo().getName());
		}
	}

	@Test
	public void findByAgentAreaIdAndNotInVisitRouteId1() {
		Dealer page = dealerRepository.findOne(1l);

	}

}
