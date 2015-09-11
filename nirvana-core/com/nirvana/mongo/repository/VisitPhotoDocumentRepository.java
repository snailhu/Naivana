package com.nirvana.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nirvana.mongo.document.VisitPhotoDocument;

public interface VisitPhotoDocumentRepository extends MongoRepository<VisitPhotoDocument, String> {

}
