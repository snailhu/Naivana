package com.nirvana.mongo.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.nirvana.domain.store.StoreOrderState;
import com.nirvana.mongo.document.StoreOrderDocument;

public interface StoreOrderDocumentRepository extends MongoRepository<StoreOrderDocument, Long> {

	// @Query("{'dealerId' : ?0, 'state' : ?1, 'createDate' : {'$gt' : ?2, '$lt' : ?3}}")
	public Page<StoreOrderDocument> findByDealerIdAndStateAndCreateDateBetweenOrderByIdDesc(Long dealerId, StoreOrderState state, Date begin, Date end, Pageable pageable);

	// @Query("{'dealerId' : ?0, 'state' : ?1}")
	public Page<StoreOrderDocument> findByDealerIdAndStateOrderByIdDesc(Long dealerId, StoreOrderState state, Pageable pageable);

	Page<StoreOrderDocument> findByStoreIdAndStateOrderByIdDesc(Long storeId, StoreOrderState state, Pageable page);

	Page<StoreOrderDocument> findByStoreIdAndStateAndCreateDateBetweenOrderByIdDesc(Long storeId, StoreOrderState state, Date start, Date end, Pageable page);

}
