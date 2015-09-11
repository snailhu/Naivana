/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年1月15日 下午2:11:05
 */
package com.nirvana.controller.dto.app;

import com.nirvana.domain.backend.goal.AgentWDNrGoal;
import com.nirvana.pojo4json.BaseDTO;

public class ThisDayGoalDTO extends BaseDTO<AgentWDNrGoal> {
	/** 日目标销售额 */
	private Float dayNr;

	/** 周目标销售额 */
	private Float weekNr;


	@Override
	public BaseDTO<AgentWDNrGoal> convert(AgentWDNrGoal domain) {
		if(domain==null)
			return null;
		ThisDayGoalDTO dto = new ThisDayGoalDTO();
		dto.setDayNr(domain.getDayNr());
		dto.setWeekNr(domain.getWeekNr());
		return dto;
	}


	public Float getDayNr() {
		return dayNr;
	}


	public void setDayNr(Float dayNr) {
		this.dayNr = dayNr;
	}


	public Float getWeekNr() {
		return weekNr;
	}


	public void setWeekNr(Float weekNr) {
		this.weekNr = weekNr;
	}

}
