package com.nirvana.repository.dealer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.dealer.DealerOrderItem;
import com.nirvana.domain.dealer.DealerOrderItemPK;

public interface DealerOrderItemRepository extends JpaRepository<DealerOrderItem, DealerOrderItemPK> {

	@Query("delete from DealerOrderItem d where d.pk.order.id=?1")
	@Modifying
	public int deleteByDealerOrderId(long orderId);

}
