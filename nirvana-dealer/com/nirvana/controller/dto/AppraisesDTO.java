/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015��2��27�� ����3:06:47
 */
package com.nirvana.controller.dto;

import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.service.pojo.Appraises;

/**
 * @Title:AppraisesDTO.java
 * @Description:
 * @Version:v1.0
 */
public class AppraisesDTO extends BaseDTO<Appraises> {
	// ��Ʒ�������
	private Integer product;

	// ����
	private Integer delivery;

	// ����̬��
	private Integer manner;

	// ����
	private String note;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nirvana.pojo4json.BaseDTO#convert(java.lang.Object)
	 */
	@Override
	public BaseDTO<Appraises> convert(Appraises domain) {
		AppraisesDTO dto = new AppraisesDTO();
		if (domain == null)
			return dto;

		dto.setDelivery(domain.getDelivery());
		dto.setManner(domain.getManner());
		dto.setNote(domain.getNote());
		dto.setProduct(domain.getProduct());
		return dto;
	}

	public Integer getProduct() {
		return product;
	}

	public void setProduct(Integer product) {
		this.product = product;
	}

	public Integer getDelivery() {
		return delivery;
	}

	public void setDelivery(Integer delivery) {
		this.delivery = delivery;
	}

	public Integer getManner() {
		return manner;
	}

	public void setManner(Integer manner) {
		this.manner = manner;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
