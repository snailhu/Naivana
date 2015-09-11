package com.nirvana.repository.store;

import java.util.Date;
import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.store.StoreOrder;
import com.nirvana.domain.store.StoreOrderState;

public interface StoreOrderRepository extends JpaRepository<StoreOrder, Long> {

	@Query("from StoreOrder so where so.store.agentArea.id=?1 and so.isAgentHelped=?2 order by so.createDate desc")
	public Page<StoreOrder> findByAgentAreaIdAndisAgentHelped(int areaId, boolean helped, Pageable pageable);

	@Query("from StoreOrder so where so.orderNo = ?1")
	public StoreOrder findByOrderNo(Long orderNo);

	@Query("from StoreOrder so where so.orderNo = ?1")
	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	public StoreOrder findByOrderNoLocked(Long orderNo);

	@Query("from StoreOrder so where so.dealer.id = ?1 and so.state=?2 order by so.createDate desc")
	public Page<StoreOrder> findByDealerIdAndState(long dealerId, StoreOrderState state, Pageable pageable);

	public Page<StoreOrder> findByStoreAgentAreaIdAndIsAgentHelpedAndCreateDateBetweenOrderByIdDesc(int areaId, boolean helped, Date start, Date end, Pageable pageable);

	/**
	 * between边界相当于>=and<=
	 */
	Page<StoreOrder> findByDealerIdAndStateAndCreateDateBetweenOrderByIdDesc(long dealerId, StoreOrderState state, Date start, Date end, Pageable pageable);

	Page<StoreOrder> findByStoreIdOrderByIdDesc(Long storeId, Pageable page);

	Page<StoreOrder> findByStoreIdAndStateOrderByIdDesc(Long storeId, StoreOrderState state, Pageable page);

	Page<StoreOrder> findByStoreIdAndCreateDateBetweenOrderByIdDesc(Long storeId, Date start, Date end, Pageable page);

	Page<StoreOrder> findByStoreIdAndStateAndCreateDateBetweenOrderByIdDesc(Long storeId, StoreOrderState state, Date start, Date end, Pageable page);

	@Modifying
	@Query("delete StoreOrder so where so.finishDate<?1 and so.state=?2")
	public Integer deleteByDateAndState(Date date, StoreOrderState state);

	@Query("from StoreOrder so where so.store.agentArea.id=?1 and so.isAgentHelped=?2 and so.createDate >=?3 and so.createDate <?4")
	public List<StoreOrder> findByAreaIdAndisAgentHelpedAndDate(Integer id, boolean b, Date startTime, Date endTime);

}
