/**
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年4月28日 下午1:09:36
 */
package com.nirvana.controller.dto.web;

import com.nirvana.domain.backend.goal.DirectorAreaNrGoal;
import com.nirvana.pojo4json.BaseDTO;

/**
 * @Description
 * @Version 1.0
 */
public class WebDirectorAreaNrGoalDTO extends BaseDTO<DirectorAreaNrGoal> {

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
	public BaseDTO<DirectorAreaNrGoal> convert(DirectorAreaNrGoal domain) {
		WebDirectorAreaNrGoalDTO dto = new WebDirectorAreaNrGoalDTO();
		if (domain == null) {
			return dto;
		}
		if (domain.getArea().getDirector() != null) {
			dto.setPosition(domain.getArea().getDirector().getType().name());
			dto.setPositionName(domain.getArea().getDirector().getType().getName());
		}
		dto.setDate(domain.getDate());
		dto.setId(domain.getId());
		dto.setIsAlloted(domain.getIsAlloted());
		dto.setNr(domain.getNr());
		dto.setAreaId(domain.getArea().getId());
		dto.setAreaName(domain.getArea().getName());
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
	 * @return the date
	 */
	public Integer getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Integer date) {
		this.date = date;
	}

	/**
	 * @return the nr
	 */
	public Float getNr() {
		return nr;
	}

	/**
	 * @param nr
	 *            the nr to set
	 */
	public void setNr(Float nr) {
		this.nr = nr;
	}

	/**
	 * @return the isAlloted
	 */
	public Boolean getIsAlloted() {
		return isAlloted;
	}

	/**
	 * @param isAlloted
	 *            the isAlloted to set
	 */
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
