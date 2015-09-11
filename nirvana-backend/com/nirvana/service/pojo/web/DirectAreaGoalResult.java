package com.nirvana.service.pojo.web;

import java.util.List;

import com.nirvana.domain.backend.area.AgentArea;
import com.nirvana.domain.backend.goal.AgentAreaNrGoal;
import com.nirvana.domain.backend.goal.DirectorAreaNrGoal;

public class DirectAreaGoalResult {

	private DirectorAreaNrGoal directorAreaNrGoal;

	private List<AgentAreaNrGoal> agentAreaNrGoals;

	private List<AgentArea> agentAreas;

	public DirectAreaGoalResult() {
	}

	public DirectorAreaNrGoal getDirectorAreaNrGoal() {
		return directorAreaNrGoal;
	}

	public void setDirectorAreaNrGoal(DirectorAreaNrGoal directorAreaNrGoal) {
		this.directorAreaNrGoal = directorAreaNrGoal;
	}

	public List<AgentAreaNrGoal> getAgentAreaNrGoals() {
		return agentAreaNrGoals;
	}

	public void setAgentAreaNrGoals(List<AgentAreaNrGoal> agentAreaNrGoals) {
		this.agentAreaNrGoals = agentAreaNrGoals;
	}

	public List<AgentArea> getAgentAreas() {
		return agentAreas;
	}

	public void setAgentAreas(List<AgentArea> agentAreas) {
		this.agentAreas = agentAreas;
	}

}
