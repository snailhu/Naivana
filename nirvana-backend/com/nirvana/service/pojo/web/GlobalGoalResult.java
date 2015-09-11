package com.nirvana.service.pojo.web;

import java.util.List;

import com.nirvana.domain.backend.area.BigArea;
import com.nirvana.domain.backend.goal.BigAreaNrGoal;

public class GlobalGoalResult {

	private boolean isAlloted;

	private List<BigAreaNrGoal> bigAreaNrGoals;

	private List<BigArea> bigAreas;

	public GlobalGoalResult() {
	}

	public boolean isAlloted() {
		return isAlloted;
	}

	public void setAlloted(boolean isAlloted) {
		this.isAlloted = isAlloted;
	}

	public List<BigAreaNrGoal> getBigAreaNrGoals() {
		return bigAreaNrGoals;
	}

	public void setBigAreaNrGoals(List<BigAreaNrGoal> bigAreaNrGoals) {
		this.bigAreaNrGoals = bigAreaNrGoals;
	}

	public List<BigArea> getBigAreas() {
		return bigAreas;
	}

	public void setBigAreas(List<BigArea> bigAreas) {
		this.bigAreas = bigAreas;
	}

}
