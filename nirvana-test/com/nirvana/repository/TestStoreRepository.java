package com.nirvana.repository;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nirvana.domain.backend.area.AgentArea;
import com.nirvana.domain.store.Store;
import com.nirvana.repository.backend.area.AgentAreaRepository;
import com.nirvana.repository.store.StoreRepository;

@ContextConfiguration(value = { "classpath:applicationContext.xml", "classpath:applicationContext-jpa.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestStoreRepository {

	@Resource
	StoreRepository storeRepository;
	@Resource
	AgentAreaRepository agentAreaRepository;

	@Test
	public void findByAgentAreaIdAndNotInVisitRouteId() {
		AgentArea agentArea = agentAreaRepository.findOne(1);
		long time = System.currentTimeMillis();
		List<Store> list = storeRepository.findInVisitRouteStores(3L);
		Page<Store> page = storeRepository.findByAgentAreaIdAndNotInList(1, list, new PageRequest(0, 100));
		for (Store store : page) {
			System.out.println(store.getStorefrontInfo().getName());
		}
	}
}
