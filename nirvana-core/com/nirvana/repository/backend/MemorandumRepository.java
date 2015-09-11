package com.nirvana.repository.backend;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.backend.Memorandum;
import com.nirvana.domain.backend.area.AgentArea;

public interface MemorandumRepository extends JpaRepository<Memorandum, Long> {

	@Query("from Memorandum md where md.area.id= ?1")
	List<Memorandum> findByEmployeeId(long employeeId);

	List<Memorandum> findByAreaOrderByEditDateDesc(AgentArea agentArea);

}
