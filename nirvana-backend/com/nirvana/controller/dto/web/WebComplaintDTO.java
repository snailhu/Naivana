/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年2月14日 下午6:11:20
 */
package com.nirvana.controller.dto.web;

import java.util.Date;

import com.nirvana.domain.backend.Complaint;
import com.nirvana.pojo4json.BaseDTO;

/**
 * @Title:WebComplaintDTO.java
 * @Description:
 * @Version:v1.0
 */
public class WebComplaintDTO extends BaseDTO<Complaint> {
	/** ID */
	private Integer id = 1;

	/** 投诉日期 */

	private Date createDate = new Date();

	/** 投诉内容 */

	private String content = "tao is sb";

	/** 投诉类型 */

	private String type = "xx";

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nirvana.pojo4json.BaseDTO#convert(java.lang.Object)
	 */
	@Override
	public BaseDTO<Complaint> convert(Complaint c) {
		WebComplaintDTO complaintBean = new WebComplaintDTO();
		if (c == null) {
			return complaintBean;
		}
		complaintBean.setContent(c.getContent());
		complaintBean.setCreateDate(c.getCreateDate());
		complaintBean.setId(c.getId());
		complaintBean.setType(c.getType().name());

		return complaintBean;
	}
}
