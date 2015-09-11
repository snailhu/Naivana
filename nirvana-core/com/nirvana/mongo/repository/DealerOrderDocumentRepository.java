package com.nirvana.mongo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.nirvana.domain.dealer.DealerOrderState;
import com.nirvana.mongo.document.DealerOrderDocument;

public interface DealerOrderDocumentRepository extends MongoRepository<DealerOrderDocument, Long> {

	@Query("{'dealerId' : ?0}")
	public Page<DealerOrderDocument> findByDealerId(long dealerId, Pageable pageable);

	@Query("{'dealerId' : ?0, 'state' : ?1}")
	public Page<DealerOrderDocument> findByDealerIdAndState(long dealerId, DealerOrderState state, Pageable pageable);

}
