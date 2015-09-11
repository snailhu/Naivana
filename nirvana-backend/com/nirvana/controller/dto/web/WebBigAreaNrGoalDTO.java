/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年5月19日 上午11:10:30
 */
package com.nirvana.controller.dto.web;

import com.nirvana.domain.backend.goal.BigAreaNrGoal;
import com.nirvana.pojo4json.BaseDTO;

/**
 * @Title:WebBigAreaNrGoalDTO.java
 * @Description:
 * @Version:v1.0
 */
public class WebBigAreaNrGoalDTO extends BaseDTO<BigAreaNrGoal> {
	/** ID */
	private Long id;

	/** 表示年月的整形，比如201006表示2010年06月。 */
	private Integer date;

	/** 销售额（元） */
	private Float nr;

	private Boolean isAlloted;

	private Integer areaId;
	private String areaName;

	private String position;
	private String positionName;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nirvana.pojo4json.BaseDTO#convert(java.lang.Object)
	 */
	@Override
	public BaseDTO<BigAreaNrGoal> convert(BigAreaNrGoal domain) {
		WebBigAreaNrGoalDTO dto = new WebBigAreaNrGoalDTO();
		if (domain == null) {
			return dto;
		}
		if (domain.getArea().getManager() != null) {
			dto.setPositionName(domain.getArea().getManager().getType()
					.getName());
			dto.setPosition(domain.getArea().getManager().getType().name());
		}
		dto.setDate(domain.getDate());
		dto.setId(domain.getId());
		dto.setIsAlloted(domain.getIsAlloted());
		dto.setNr(domain.getNr());
		dto.setAreaId(domain.getArea().getId());
		dto.setAreaName(domain.getArea().getName());
		return dto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public Float getNr() {
		return nr;
	}

	public void setNr(Float nr) {
		this.nr = nr;
	}

	public Boolean getIsAlloted() {
		return isAlloted;
	}

	public void setIsAlloted(Boolean isAlloted) {
		this.isAlloted = isAlloted;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

}
