/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年4月7日 下午1:43:30
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

	/** 是否为业务员帮助下单 */
	private Boolean isAgentHelped = true;

	/** 订单状态 */
	private String state = StoreOrderState.FINISHHANDLE.getName();

	/** 应付款(实际需要支付的费用) */
	private Float payablefee = 0f;

	/** 订单创建时间 */
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
