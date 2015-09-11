package com.nirvana.repository.dealer;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.dealer.Dealer;
import com.nirvana.repository.NirvanaRepository;

public interface DealerRepository extends NirvanaRepository<Dealer, Long> {

	@Query("select dl from Dealer dl where dl.isDirect=?1")
	public Page<Dealer> findByIsDirect(boolean isDirect, Pageable pageable);

	@Query("select dl from Dealer dl where dl.isDirect=?1 and dl.directorArea.managerArea.id=?2")
	public Page<Dealer> findByIsDirectAndManagerAreaId(boolean isDirect, int areaId, Pageable pageable);

	@Query("select d from Dealer d where d.agentArea.id=?1 and d not in (?2)")
	public Page<Dealer> findByAgentAreaIdAndNotInList(int agentAreaId, List<Dealer> dealers, Pageable pageable);

	@Query("select d from Dealer d where d.agentArea.id=?1")
	public Page<Dealer> findByAgentAreaId(int agentAreaId, Pageable pageable);

	@Query(value = "select dealer0_.*,dealer0_1_.area_id,dealer0_1_.dealer_id from dealer_dealer dealer0_ left outer join backend_area_agentarea_dealer_directstore dealer0_1_ on dealer0_.id=dealer0_1_.dealer_id right join backend_visitroutenode nodes1_ on nodes1_.dealer_id=dealer0_.id where dealer0_.id is not null and nodes1_.route_id=?1", nativeQuery = true)
	public List<Dealer> findInVisitRouteDealers(long routeId);

	@Query("select d from Dealer d where d.directorArea.id=?1 and d.isDirect=?2")
	public Set<Dealer> findByDirectorAreaIdAndIsDirect(int directAreaId, boolean isDirect);

	@Query("select d from Dealer d where d.directorArea.id=?1 and d.isDirect=true and d.agentArea.id=null")
	public List<Dealer> findByDirectorAreaIdAndTrueIsDirectAndNullAgentAreaId(int directAreaId);

	@Query("select d from Dealer d where d.codeInERP=?1")
	public Dealer findByCodeIERP(String code);

	@Modifying
	@Query("update Dealer d set d.category.id=?2 where d.category.id=?1")
	public int updateAgentCategoryByAgentCategory(long oldId, long newId);

	@Query("select d from Dealer d where d.channel.code=?1")
	public List<Dealer> findChannelIdByChannel(String code);

}
