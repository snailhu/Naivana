package com.nirvana.repository.backend;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.backend.VisitRoute;

public interface VisitRouteRepository extends JpaRepository<VisitRoute, Long> {

	@Query("from VisitRoute vr where vr.area.id=?1 and vr.code =?2")
	public VisitRoute findByAgentAreaIdAndRouteCode(int agentAreaId, int routeCode);

	@Query("from VisitRoute vr where vr.area.id=?1 and vr.code =?2")
	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	public VisitRoute findByAgentAreaIdAndRouteCodeLocked(int agentAreaId, int routeCode);

	@Query("from VisitRoute vr where vr.area.id=?1")
	public List<VisitRoute> findByAgentAreaId(Integer id);

}
