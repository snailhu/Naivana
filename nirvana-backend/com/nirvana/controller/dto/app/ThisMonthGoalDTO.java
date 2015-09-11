package com.nirvana.controller.dto.app;

import com.nirvana.domain.backend.goal.AgentAreaNrGoal;
import com.nirvana.pojo4json.BaseDTO;

public class ThisMonthGoalDTO extends BaseDTO<AgentAreaNrGoal> {
	private float nr;

	public ThisMonthGoalDTO() {
	}

	public float getNr() {
		return nr;
	}

	public void setNr(float nr) {
		this.nr = nr;
	}

	@Override
	public BaseDTO<AgentAreaNrGoal> convert(AgentAreaNrGoal domain) {
		if (domain == null)
			return null;
		ThisMonthGoalDTO dto = new ThisMonthGoalDTO();
		dto.setNr(domain.getNr());
		return dto;
	}
}
