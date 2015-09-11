package com.nirvana.service.pojo.web;

import java.util.List;

import com.nirvana.domain.backend.area.ManagerArea;
import com.nirvana.domain.backend.goal.BigAreaNrGoal;
import com.nirvana.domain.backend.goal.ManagerAreaNrGoal;

public class BigAreaGoalResult {

	private BigAreaNrGoal bigAreaNrGoal;

	private List<ManagerAreaNrGoal> managerAreaNrGoals;

	private List<ManagerArea> managerAreas;

	public BigAreaGoalResult() {
	}

	public BigAreaNrGoal getBigAreaNrGoal() {
		return bigAreaNrGoal;
	}

	public void setBigAreaNrGoal(BigAreaNrGoal bigAreaNrGoal) {
		this.bigAreaNrGoal = bigAreaNrGoal;
	}

	public List<ManagerAreaNrGoal> getManagerAreaNrGoals() {
		return managerAreaNrGoals;
	}

	public void setManagerAreaNrGoals(List<ManagerAreaNrGoal> managerAreaNrGoals) {
		this.managerAreaNrGoals = managerAreaNrGoals;
	}

	public List<ManagerArea> getManagerAreas() {
		return managerAreas;
	}

	public void setManagerAreas(List<ManagerArea> managerAreas) {
		this.managerAreas = managerAreas;
	}

}
