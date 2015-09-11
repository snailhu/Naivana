package com.nirvana.test;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nirvana.service.DuplicateCheckService;


@ContextConfiguration({ "classpath:applicationContext.xml", "classpath:applicationContext-jpa.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class HibernateTest {

	@Resource
	private DuplicateCheckService duplicateCheckService;
	
	@Test
	public void read(){
		
		boolean ex = duplicateCheckService.backEndUserNameExist("user0");
	
		System.out.println(ex);
	            
		
		
	}
	
}
