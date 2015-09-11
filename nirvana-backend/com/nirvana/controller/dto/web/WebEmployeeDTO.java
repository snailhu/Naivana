package com.nirvana.controller.dto.web;

import com.nirvana.domain.backend.Employee;
import com.nirvana.domain.backend.usersys.BackEndUser;
import com.nirvana.pojo4json.BaseDTO;

public class WebEmployeeDTO extends BaseDTO<Employee> {
	private Long id;
	private String nameCode;
	private String phone;
	private String realName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameCode() {
		return nameCode;
	}

	public void setNameCode(String nameCode) {
		this.nameCode = nameCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Override
	public BaseDTO<Employee> convert(Employee domain) {
		WebEmployeeDTO dto = new WebEmployeeDTO();
		dto.setId(domain.getId());
		dto.setRealName(domain.getName());
		BackEndUser user = domain.getUser();
		if (user != null) {
			dto.setNameCode(user.getUsername());
			dto.setPhone(user.getPhone());
		}
		return dto;
	}
}
