package com.nirvana.erp.repository;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.erp.domain.PepsiCustomer;

@ContextConfiguration(value = { "classpath:applicationContext.xml", "classpath:applicationContext-jpa.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestPepsiCustomerRepository {
	@Resource
	private PepsiCustomerRepository pepsiCustomerRepository;

	@Test
	@Transactional
	public void testAddr() {
		PepsiCustomer customer = pepsiCustomerRepository.getOne("G00132");
		String addr = customer.getAdd1();
		System.out.println(addr);
		System.out.println();
		String[] strings = addr.split("\n");
		String addr1 = strings[0].substring(5).trim();
		String addr2 = strings[1].substring(5).trim();
		String code = strings[2].substring(5).trim();
		String city = strings[3].substring(4).trim();
		String country = strings[4].substring(4).trim();
		String town = strings[5].substring(4).trim();
		System.out.println(addr1);
		System.out.println(addr2);
		System.out.println(code);
		System.out.println(city);
		System.out.println(country);
		System.out.println(town);
	}

}
