package com.nirvana.repository.backend;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.backend.PepsiPromotion;
import com.nirvana.domain.backend.PromotionState;

public interface PepsiPromotionRepository extends JpaRepository<PepsiPromotion, Integer> {

	@Query("from PepsiPromotion p where p.state=?1 order by p.createDate DESC")
	public Page<PepsiPromotion> findByStateOrderByCreateDate(PromotionState state, Pageable pageable);

	@Query("from PepsiPromotion p where p.state=?1 order by p.passDate DESC")
	public Page<PepsiPromotion> findByStateOrderByPassDate(PromotionState state, Pageable pageable);

	@Query("from PepsiPromotion p where p.state=?1 and p.channel.code=?2 order by p.createDate DESC")
	public Page<PepsiPromotion> findByStateAndChannelId(PromotionState state, String channelCode, Pageable pageable);

}
