package com.nirvana.controller.dto;

import com.nirvana.domain.backend.Position;
import com.nirvana.domain.backend.PositionType;
import com.nirvana.pojo4json.BaseDTO;

public class PositionDto extends BaseDTO<Position> {

	private long id;

	private String positionName;

	@Override
	public BaseDTO<Position> convert(Position domain) {
		PositionDto dto = new PositionDto();
		if (domain == null) {
			return dto;
		}
		dto.setId(domain.getId());
		PositionType type = domain.getType();
		if (type == PositionType.AGENT) {
			dto.setPositionName(domain.getAgentArea().getDesc() + type.getName());
		} else if (type == PositionType.TDS) {
			dto.setPositionName(domain.getDirectorArea().getName() + type.getName());
		} else if (type == PositionType.TDM) {
			dto.setPositionName(domain.getmManagerArea().getName() + type.getName());
		} else if (type == PositionType.CLERK) {
			dto.setPositionName(domain.getcManagerArea().getName() + type.getName());
		} else if (type == PositionType.UM) {
			dto.setPositionName(domain.getmBigArea().getName() + type.getName());
		} else if (type == PositionType.FSIS) {
			dto.setPositionName(domain.getfBigArea().getName() + type.getName());
		} else {
			dto.setPositionName(type.getName());
		}
		return dto;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

}
