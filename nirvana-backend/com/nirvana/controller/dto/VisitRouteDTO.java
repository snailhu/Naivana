/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015��5��26�� ����10:32:18
 */
package com.nirvana.controller.dto;

import java.util.List;

import com.nirvana.domain.backend.VisitRoute;
import com.nirvana.domain.backend.VisitRouteNode;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;

/**
 * @Title:VisitRouteDTO.java
 * @Description:
 * @Version:v1.0
 */
public class VisitRouteDTO extends BaseDTO<VisitRoute> {
	/** ID */
	private Long id;

	/** ·�ߴ��ţ�����֮�󲻿��޸ġ� */
	private Integer code;

	/** ��·��������CR���� */
	private Integer agentAreaId;

	/** ��·�߰����Ľڵ㡣 */
	private List<VisitRouteNodeDTO> nodes;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nirvana.pojo4json.BaseDTO#convert(java.lang.Object)
	 */
	@Override
	public BaseDTO<VisitRoute> convert(VisitRoute domain) {
		VisitRouteDTO dto = new VisitRouteDTO();
		if (domain == null) {
			return dto;
		}
		dto.setAgentAreaId(domain.getArea().getId());
		dto.setCode(domain.getCode());
		dto.setId(domain.getId());
		Converter<VisitRouteNode, VisitRouteNodeDTO> converter = DTOContext
				.getConverter(VisitRouteNodeDTO.class);
		dto.setNodes(converter.convert(domain.getNodes()));
		return dto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getAgentAreaId() {
		return agentAreaId;
	}

	public void setAgentAreaId(Integer agentAreaId) {
		this.agentAreaId = agentAreaId;
	}

	public List<VisitRouteNodeDTO> getNodes() {
		return nodes;
	}

	public void setNodes(List<VisitRouteNodeDTO> nodes) {
		this.nodes = nodes;
	}
}
