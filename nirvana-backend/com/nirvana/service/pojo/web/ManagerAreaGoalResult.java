package com.nirvana.service.pojo.web;

import java.util.List;

import com.nirvana.domain.backend.area.DirectorArea;
import com.nirvana.domain.backend.goal.DirectorAreaNrGoal;
import com.nirvana.domain.backend.goal.ManagerAreaNrGoal;

public class ManagerAreaGoalResult {

	private ManagerAreaNrGoal managerAreaNrGoal;

	private List<DirectorAreaNrGoal> directorAreaNrGoals;

	private List<DirectorArea> directorAreas;

	public ManagerAreaGoalResult() {
	}

	public ManagerAreaNrGoal getManagerAreaNrGoal() {
		return managerAreaNrGoal;
	}

	public void setManagerAreaNrGoal(ManagerAreaNrGoal managerAreaNrGoal) {
		this.managerAreaNrGoal = managerAreaNrGoal;
	}

	public List<DirectorAreaNrGoal> getDirectorAreaNrGoals() {
		return directorAreaNrGoals;
	}

	public void setDirectorAreaNrGoals(List<DirectorAreaNrGoal> directorAreaNrGoals) {
		this.directorAreaNrGoals = directorAreaNrGoals;
	}

	public List<DirectorArea> getDirectorAreas() {
		return directorAreas;
	}

	public void setDirectorAreas(List<DirectorArea> directorAreas) {
		this.directorAreas = directorAreas;
	}

}
