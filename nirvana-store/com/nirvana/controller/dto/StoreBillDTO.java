/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015��4��7�� ����1:43:30
 */
package com.nirvana.controller.dto;

import java.util.Date;

import com.nirvana.domain.store.StoreOrderState;
import com.nirvana.utils.DateUtil;

/**
 * @Title:StoreBillDTO.java
 * @Description:
 * @Version:v1.0
 */
public class StoreBillDTO {

	private Long id = 1l;

	/** �Ƿ�Ϊҵ��Ա�����µ� */
	private Boolean isAgentHelped = true;

	/** ����״̬ */
	private String state = StoreOrderState.FINISHHANDLE.getName();

	/** Ӧ����(ʵ����Ҫ֧���ķ���) */
	private Float payablefee = 0f;

	/** ��������ʱ�� */
	private String createDate = DateUtil.dateToString(new Date(), "yyyy/MM/dd");

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsAgentHelped() {
		return isAgentHelped;
	}

	public void setIsAgentHelped(Boolean isAgentHelped) {
		this.isAgentHelped = isAgentHelped;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Float getPayablefee() {
		return payablefee;
	}

	public void setPayablefee(Float payablefee) {
		this.payablefee = payablefee;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
