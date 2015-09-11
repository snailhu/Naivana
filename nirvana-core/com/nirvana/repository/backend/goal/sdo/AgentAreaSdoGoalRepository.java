package com.nirvana.repository.backend.goal.sdo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.backend.goal.sdo.AgentAreaSdoGoal;

public interface AgentAreaSdoGoalRepository extends JpaRepository<AgentAreaSdoGoal, Long> {

	@Query("from AgentAreaSdoGoal asg where asg.agentArea.id=?1 and asg.date=?2")
	public AgentAreaSdoGoal findByAgentAreaIdAndDate(int id, int date);

}
