package com.nirvana.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nirvana.mongo.document.VisitRecordDocument;

public interface VisitRecordDocumentRepository extends MongoRepository<VisitRecordDocument, Long> {

}
