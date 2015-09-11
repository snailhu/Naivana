package com.nirvana.controller.dto.web;

import java.util.List;

public class WebBigAreaGoalAndSonDTO {
	WebBigAreaNrGoalDTO bigAreaNrGoal;
	List<WebManagerAreaNrGoalDTO> managerAreaNrGoals;
	List<WebManagerAreaDTO> managerAreas;

	public WebBigAreaGoalAndSonDTO(WebBigAreaNrGoalDTO bigAreaNrGoal, List<WebManagerAreaNrGoalDTO> managerAreaNrGoals, List<WebManagerAreaDTO> managerAreas) {
		super();
		this.bigAreaNrGoal = bigAreaNrGoal;
		this.managerAreaNrGoals = managerAreaNrGoals;
		this.managerAreas = managerAreas;
	}

	public WebBigAreaGoalAndSonDTO() {
	}

	public WebBigAreaNrGoalDTO getBigAreaNrGoal() {
		return bigAreaNrGoal;
	}

	public void setBigAreaNrGoal(WebBigAreaNrGoalDTO bigAreaNrGoal) {
		this.bigAreaNrGoal = bigAreaNrGoal;
	}

	public List<WebManagerAreaNrGoalDTO> getManagerAreaNrGoals() {
		return managerAreaNrGoals;
	}

	public void setManagerAreaNrGoals(List<WebManagerAreaNrGoalDTO> managerAreaNrGoals) {
		this.managerAreaNrGoals = managerAreaNrGoals;
	}

	public List<WebManagerAreaDTO> getManagerAreas() {
		return managerAreas;
	}

	public void setManagerAreas(List<WebManagerAreaDTO> managerAreas) {
		this.managerAreas = managerAreas;
	}
}
