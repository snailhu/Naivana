package com.nirvana.repository.dealer;

import java.util.Date;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.InventoriesCheckHistory;

public interface InventoriesCheckHistoryRepository extends
		JpaRepository<InventoriesCheckHistory, Long> {

	Page<InventoriesCheckHistory> findByDealerIdAndDateBetweenOrderByDateDesc(
			Long dealerId, Date start, Date end, Pageable page);

	Page<InventoriesCheckHistory> findByDealerInAndDateBetweenOrderByDateDesc(
			Set<Dealer> dealers, Date start, Date end, Pageable page);
}
