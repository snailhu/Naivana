package com.nirvana.repository.dealer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.dealer.DealerStoreCategory;

public interface DealerStoreCategoryRepository extends JpaRepository<DealerStoreCategory, Long> {

	@Query("from DealerStoreCategory dc where dc.dealer.id =?1")
	public List<DealerStoreCategory> findByDealerId(long dealerId);

	@Query("from DealerStoreCategory dc where dc.dealer.id =?1 and dc.categoryName=?2")
	public DealerStoreCategory findByDealerIdAndCategoryName(long dealerId, String category);

}
