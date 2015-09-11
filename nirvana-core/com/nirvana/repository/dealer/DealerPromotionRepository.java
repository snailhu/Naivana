package com.nirvana.repository.dealer;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.DealerPromotion;
import com.nirvana.domain.dealer.DealerStoreCategory;

public interface DealerPromotionRepository extends
		JpaRepository<DealerPromotion, Long> {

	Page<DealerPromotion> findByDealerIdOrderByEditDateDesc(long dealerId,
			Pageable page);

	Set<DealerPromotion> findByDealerIdAndCategoriesId(long dealerId,
			long categoryId);

	@Query(value = "select * from dealer_promotion dp,(select id from dealer_promotion where dealer_id=:dealerId and endDate >=:now and beginDate <=:now2)dpid,(select promotion_id from dealer_dealerpromotion_product where product_code=:productCode)p_id,(select promotion_id from dealer_dealerpromotion_category where category_id=:categoryId)c_id where dp.id=dpid.id and dp.id=p_id.promotion_id and dp.id=c_id.promotion_id", nativeQuery = true)
	DealerPromotion findByDealerAndCategoriesAndProductsCodeAndEndDateGreaterThanAndBeginDateLessThan(
			@Param("dealerId") Long dealer,
			@Param("categoryId") Long categoryId,
			@Param("productCode") String productCode, @Param("now") int now,
			@Param("now2") int now2);

	/**
	 * endDate包含
	 * 
	 * @param dealer
	 * @param product
	 * @param category
	 * @param start
	 * @param end
	 * @return
	 */
	@Query(value = "select count(dp.id) from dealer_promotion dp,(select id from dealer_promotion where dealer_id=:dealerId and endDate >=:start and beginDate <:end)dpid,(select promotion_id from dealer_dealerpromotion_product where product_code=:productCode)p_id,(select promotion_id from dealer_dealerpromotion_category where category_id=:categoryId)c_id where dp.id=dpid.id and dp.id=p_id.promotion_id and dp.id=c_id.promotion_id", nativeQuery = true)
	int countByDealerAndProductsAndCategoriesAndEndDateGreaterThanAndBeginDateLessThan(
			@Param("dealerId") Long dealerId,
			@Param("productCode") String productCode,
			@Param("categoryId") Long category, @Param("start") int start,
			@Param("end") int end);

	/**
	 * beginDate包含
	 * 
	 * @param dealer
	 * @param product
	 * @param category
	 * @param start
	 * @param end
	 * @return
	 */
	@Query(value = "select count(dp.id) from dealer_promotion dp,(select id from dealer_promotion where dealer_id=:dealerId and endDate >:start and beginDate <=:end)dpid,(select promotion_id from dealer_dealerpromotion_product where product_code=:productCode)p_id,(select promotion_id from dealer_dealerpromotion_category where category_id=:categoryId)c_id where dp.id=dpid.id and dp.id=p_id.promotion_id and dp.id=c_id.promotion_id", nativeQuery = true)
	int countByDealerAndProductsAndCategoriesAndEndDateGreaterThanAndBeginDateLessThanB(
			@Param("dealerId") Long dealerId,
			@Param("productCode") String productCode,
			@Param("categoryId") Long category, @Param("start") int start,
			@Param("end") int end);

	/**
	 * 两边均包含
	 * 
	 * @param dealer
	 * @param product
	 * @param category
	 * @param start
	 * @param end
	 * @return
	 */
	@Query(value = "select count(dp.id) from dealer_promotion dp,(select id from dealer_promotion where dealer_id=:dealerId and endDate >=:start and beginDate <=:end)dpid,(select promotion_id from dealer_dealerpromotion_product where product_code=:productCode)p_id,(select promotion_id from dealer_dealerpromotion_category where category_id=:categoryId)c_id where dp.id=dpid.id and dp.id=p_id.promotion_id and dp.id=c_id.promotion_id", nativeQuery = true)
	int countByDealerAndProductsAndCategoriesAndEndDateGreaterThanAndBeginDateLessThanC(
			@Param("dealerId") Long dealerId,
			@Param("productCode") String productCode,
			@Param("categoryId") Long category, @Param("start") int start,
			@Param("end") int end);

//	@Query("from DealerPromotion dp where dp.dealer=?1 and dp.categories=?4 and dp.endDate>=?2 and dp.beginDate<=?3")
	Page<DealerPromotion> findByDealerAndEndDateGreaterThanAndBeginDateLessThanAndCategories(
			Dealer dealer, int now, int now2, DealerStoreCategory category, Pageable page);
}
