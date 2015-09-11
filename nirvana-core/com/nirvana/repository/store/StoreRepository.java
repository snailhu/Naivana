package com.nirvana.repository.store;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.store.Store;
import com.nirvana.repository.NirvanaRepository;

public interface StoreRepository extends NirvanaRepository<Store, Long> {

	public List<Store> findByDealerIdAndDealerCategoryIdIsNull(long dealerId);

	@Query("from Store s where s.dealer.id=null")
	public List<Store> findByNullDealer();

	@Query("from Store s where s.dealer.directorArea.managerArea.id=?1")
	public Page<Store> findBycManagerAreaId(int areaId, Pageable pageable);

	@Modifying
	@Query("update Store s set s.agentArea.id=null,s.agentCategory.id=null where s.dealer.id=?1 and s.agentArea.id=?2")
	public int updateAgentAreaAndStoreCategoryToNullByDealerIdAndAgentAreaId(long dealerId, int areaId);

	/** 通过agentAreaId和不属于此visitRouteId查找 */
	@Query("select s from Store s where s.agentArea.id=?1 and s not in (?2)")
	public Page<Store> findByAgentAreaIdAndNotInList(int areaId, List<Store> stores, Pageable pageable);

	@Query("select s from Store s where s.agentArea.id=?1")
	public Page<Store> findByAgentAreaId(int areaId, Pageable pageable);

	@Query("select s from Store s where s.dealer.id=?1 and s.agentArea.id=null")
	public List<Store> findByDealerIdAndNullAgentAreaId(long dealerId);

	@Query(value = "select store0_.* from store_store store0_ right join backend_visitroutenode nodes1_ on nodes1_.store_id=store0_.id where store0_.id is not null and nodes1_.route_id=?1", nativeQuery = true)
	public List<Store> findInVisitRouteStores(long routeId);

	@Modifying
	@Query("update Store s set s.agentCategory.id=?2 where s.agentCategory.id=?1")
	public int updateAgentCategoryByAgentCategory(long oldId, long newId);

	@Modifying
	@Query("update Store s set s.dealerCategory.id=?2 where s.dealerCategory.id=?1")
	public int updateDealerCategoryByDealerCategory(long oldId, long newId);

}
