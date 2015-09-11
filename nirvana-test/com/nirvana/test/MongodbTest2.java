package com.nirvana.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.domain.dealer.DealerOrder;
import com.nirvana.mongo.converter.impl.DealerOrderConverter;
import com.nirvana.mongo.document.DealerOrderDocument;
import com.nirvana.mongo.repository.DealerOrderDocumentRepository;
import com.nirvana.mongo.repository.StoreOrderDocumentRepository;
import com.nirvana.repository.dealer.DealerOrderRepository;
import com.nirvana.repository.store.StoreOrderRepository;

@ContextConfiguration(value = { "classpath:applicationContext.xml", "classpath:applicationContext-mongo.xml", "classpath:applicationContext-jpa.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MongodbTest2 {

	@Resource
	private StoreOrderDocumentRepository storeOrderDocumentRepository;
	@Resource
	private StoreOrderRepository storeOrderRepository;
	@Resource
	private DealerOrderRepository dealerOrderRepository;
	@Resource
	private DealerOrderDocumentRepository dealerOrderDocumentRepository;

	@Test
	@Transactional
	public void save() {
		List<DealerOrder> orders = dealerOrderRepository.findAll();
		DealerOrder order = orders.get(1);
		DealerOrderConverter converter = new DealerOrderConverter();
		DealerOrderDocument document = converter.convert(order);
		int j = 1;
		long time = System.currentTimeMillis();
		for (int i = 0; i < 100000000; i++) {
			document.setId((long) i);
			dealerOrderDocumentRepository.save(document);
			if (i == j * 100000) {
				System.out.println(((float) j / 10) + "%");
				long time2 = System.currentTimeMillis();
				System.out.println("十万次保存耗时：" + (time2 - time) + "毫秒。");
				time = time2;
				j++;
			}
		}

	}

}
