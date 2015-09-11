package com.nirvana.controller.dto.app;

import com.nirvana.domain.backend.goal.sdo.Sdo;
import com.nirvana.pojo4json.BaseDTO;

public class AppSdoDTO extends BaseDTO<Sdo> {

	// SDO目标名称。
	private String name="sdo name";

	// 执行标准
	private String criterion="标准";

	// 计算方式
	private String method="计算方式";

	// 目标值
	private String value="目标值";

	public AppSdoDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCriterion() {
		return criterion;
	}

	public void setCriterion(String criterion) {
		this.criterion = criterion;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public AppSdoDTO convert(Sdo domain) {
		AppSdoDTO dto = new AppSdoDTO();
		if(domain==null){
			return dto;
		}
		dto.setCriterion(domain.getCriterion());
		dto.setMethod(domain.getMethod());
		dto.setName(domain.getName());
		dto.setValue(domain.getValue());
		return dto;
	}

}
