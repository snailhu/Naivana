package com.nirvana.repository.backend.goal;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.backend.goal.DirectorAreaNrGoal;
import com.nirvana.repository.NirvanaRepository;

public interface DirectorAreaGoalRepository extends NirvanaRepository<DirectorAreaNrGoal, Long> {

	@Query("from DirectorAreaNrGoal dg where dg.area.id=? and dg.date=?")
	public DirectorAreaNrGoal findByAreaIdAndDate(int id, int date);

	@Modifying
	@Query("delete DirectorAreaNrGoal dg where dg.area.id=?1 and dg.date=?2")
	public int deleteByAreaIdAndDate(int areaId, int date);

}
