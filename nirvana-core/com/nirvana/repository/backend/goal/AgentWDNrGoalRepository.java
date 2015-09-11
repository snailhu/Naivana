package com.nirvana.repository.backend.goal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.backend.goal.AgentWDNrGoal;

public interface AgentWDNrGoalRepository extends JpaRepository<AgentWDNrGoal, Integer> {

	@Query("from AgentWDNrGoal ag where ag.area.agent.employee.id = ?1")
	AgentWDNrGoal findByEmployeeId(long employeeId);

}
