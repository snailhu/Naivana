package com.nirvana.repository.backend.goal;

import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.backend.goal.BigAreaNrGoal;
import com.nirvana.repository.NirvanaRepository;

public interface BigAreaGoalRepository extends NirvanaRepository<BigAreaNrGoal, Long> {

	@Query("from BigAreaNrGoal ba where ba.area.id=?1 and ba.date =?2")
	public BigAreaNrGoal findByAreaIdAndDate(int areaId, int date);

}
