package com.nirvana.controller.dto.web;

import java.util.List;
import com.nirvana.domain.backend.goal.sdo.AgentAreaSdoGoal;
import com.nirvana.domain.backend.goal.sdo.Sdo;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;

public class WebAgentAreaSdoGoalDTO extends BaseDTO<AgentAreaSdoGoal> {
	/** ID */
	private Long id;

	/** SDOĿ��ʵ�塣 */
	private List<WebSdoDTO> sdos;

	/** ������CR���� */
	private Integer agentAreaId;
	private String agentAreaName;

	/** ���ڣ����¡�201008��ʾ2010��8�¡� */
	private Integer date;

	@Override
	public BaseDTO<AgentAreaSdoGoal> convert(AgentAreaSdoGoal domain) {
		WebAgentAreaSdoGoalDTO dto = new WebAgentAreaSdoGoalDTO();
		if (domain == null) {
			return dto;
		}
		dto.setAgentAreaId(domain.getAgentArea().getId());
		dto.setAgentAreaName(domain.getAgentArea().getDesc());
		dto.setDate(domain.getDate());
		dto.setId(domain.getId());
		Converter<Sdo, WebSdoDTO> converter = DTOContext.getConverter(WebSdoDTO.class);
		dto.setSdos(converter.convert(domain.getSdos()));
		return dto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<WebSdoDTO> getSdos() {
		return sdos;
	}

	public void setSdos(List<WebSdoDTO> sdos) {
		this.sdos = sdos;
	}

	public Integer getAgentAreaId() {
		return agentAreaId;
	}

	public void setAgentAreaId(Integer agentAreaId) {
		this.agentAreaId = agentAreaId;
	}

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public String getAgentAreaName() {
		return agentAreaName;
	}

	public void setAgentAreaName(String agentAreaName) {
		this.agentAreaName = agentAreaName;
	}
}
