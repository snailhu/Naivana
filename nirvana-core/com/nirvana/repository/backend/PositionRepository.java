package com.nirvana.repository.backend;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.backend.Position;
import com.nirvana.domain.backend.PositionType;

public interface PositionRepository extends JpaRepository<Position, Integer> {

	@Query("from Position p where p.employee.id=?1 and p.type=?2")
	public List<Position> findPositionByEmployeeIdAndPositionType(long employeeId, PositionType type);

	@Query("from Position p where p.type=?1")
	public Position findByPositionType(PositionType type);

}
