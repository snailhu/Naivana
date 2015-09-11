/**
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年4月28日 下午1:22:39
 */
package com.nirvana.controller.dto.web;

import com.nirvana.domain.backend.goal.sdo.Sdo;
import com.nirvana.pojo4json.BaseDTO;

/**
 * @Description
 * @Version 1.0
 */
public class WebSdoDTO extends BaseDTO<Sdo> {

	/** ID */
	private Long id;

	/** SDO目标名称。 */
	private String name;

	/** 执行标准 */
	private String criterion;

	/** 计算方式 */
	private String method;

	/** 目标值 */
	private String value;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nirvana.pojo4json.BaseDTO#convert(java.lang.Object)
	 */
	@Override
	public BaseDTO<Sdo> convert(Sdo domain) {
		WebSdoDTO dto = new WebSdoDTO();
		if (domain == null) {
			return dto;
		}
		dto.setCriterion(domain.getCriterion());
		dto.setId(domain.getId());
		dto.setMethod(domain.getMethod());
		dto.setName(domain.getName());
		dto.setValue(domain.getValue());
		return dto;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the criterion
	 */
	public String getCriterion() {
		return criterion;
	}

	/**
	 * @param criterion
	 *            the criterion to set
	 */
	public void setCriterion(String criterion) {
		this.criterion = criterion;
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method
	 *            the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
