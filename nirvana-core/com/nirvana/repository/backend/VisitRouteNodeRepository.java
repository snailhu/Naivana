package com.nirvana.repository.backend;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.backend.VisitRouteNode;
import com.nirvana.domain.backend.VisitRouteNodePK;

public interface VisitRouteNodeRepository extends JpaRepository<VisitRouteNode, VisitRouteNodePK> {

	@Query("from VisitRouteNode vr where vr.pk.route.area.id=?1 and vr.store.dealer.id=?2")
	public List<VisitRouteNode> findByAgentAreaIdAndDealerId(int areaId, long dealerId);

	@Modifying
	@Query("from VisitRouteNode vr where vr.pk.route.area.id=?1 and vr.dealer.id=?2")
	public List<VisitRouteNode> findByAgentAreaIdAndDStoreId(int areaId, long dealerId);

	@Modifying
	@Query("delete VisitRouteNode vr where vr.store.id=?1")
	public int deleteByStoreId(long storeId);

	@Modifying
	@Query("delete VisitRouteNode vr where vr.dealer.id=?1")
	public int deleteByDealerId(long dealerId);

	@Modifying
	@Query("delete VisitRouteNode vr where vr.pk.route.id=?1")
	public int deleteByRouteId(long routeId);

}
