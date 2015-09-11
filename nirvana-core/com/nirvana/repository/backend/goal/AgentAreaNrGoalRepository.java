package com.nirvana.repository.backend.goal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.backend.goal.AgentAreaNrGoal;

public interface AgentAreaNrGoalRepository extends JpaRepository<AgentAreaNrGoal, Long> {

	@Query("from AgentAreaNrGoal aa where aa.area.id=? and aa.date=?")
	public AgentAreaNrGoal findByAreaIdAndDate(int agentAreaId, int date);

	@Modifying
	@Query("delete AgentAreaNrGoal ag where ag.area.id=?1 and ag.date=?2")
	public int deleteByAreaIdAndDate(int areaId, int date);
}
