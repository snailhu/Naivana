package com.nirvana.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.nirvana.domain.backend.Employee;

@ContextConfiguration(value = { "classpath:applicationContext.xml", "classpath:applicationContext-jpa.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestAgentManageService {

	@Resource
	private PersonnelManageService personnelManageService;

	@Test
	public void getEmployees() {

		Page<Employee> page = personnelManageService.getEmployees(1, 20);
		System.out.println(page.getSize());
	}

}
