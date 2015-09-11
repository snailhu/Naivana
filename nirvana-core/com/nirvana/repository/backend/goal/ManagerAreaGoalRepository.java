package com.nirvana.repository.backend.goal;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.backend.goal.ManagerAreaNrGoal;
import com.nirvana.repository.NirvanaRepository;

public interface ManagerAreaGoalRepository extends NirvanaRepository<ManagerAreaNrGoal, Long> {

	@Query("from ManagerAreaNrGoal mg where mg.area.id=?1 and mg.date=?2")
	public ManagerAreaNrGoal findByAreaIdAndDate(int areaId, int date);

	@Modifying
	@Query("delete ManagerAreaNrGoal mg where mg.area.id=?1 and mg.date=?2")
	public int deleteByAreaIdAndDate(int areaId, int date);

}
