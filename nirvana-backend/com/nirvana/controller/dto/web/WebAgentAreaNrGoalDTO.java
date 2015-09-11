package com.nirvana.controller.dto.web;

import com.nirvana.domain.backend.goal.AgentAreaNrGoal;
import com.nirvana.pojo4json.BaseDTO;

public class WebAgentAreaNrGoalDTO extends BaseDTO<AgentAreaNrGoal> {

	/** ID */
	private Long id;

	/** 表示年月的整形，比如201006表示2010年06月。 */
	private Integer date;

	/** 销售额（元） */
	private Float nr;

	private Integer areaId;
	private String areaName;
	private String position = "";
	private String positionName = "";

	@Override
	public BaseDTO<AgentAreaNrGoal> convert(AgentAreaNrGoal domain) {
		WebAgentAreaNrGoalDTO dto = new WebAgentAreaNrGoalDTO();
		if (domain == null) {
			return dto;
		}
		if (domain.getArea().getAgent() != null) {
			dto.setPosition(domain.getArea().getAgent().getType().name());
			dto.setPositionName(domain.getArea().getAgent().getType().getName());
		}
		dto.setDate(domain.getDate());
		dto.setId(domain.getId());
		dto.setNr(domain.getNr());
		dto.setAreaId(domain.getArea().getId());
		dto.setAreaName(domain.getArea().getDesc());
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
