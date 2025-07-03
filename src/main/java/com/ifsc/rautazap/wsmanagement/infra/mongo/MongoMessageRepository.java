package com.ifsc.rautazap.wsmanagement.infra.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoMessageRepository extends MongoRepository<MessageDocument, String> {

    List<MessageDocument> findByToUserIdAndDeliveredFalseOrderByTimestampDesc(String toUserId);
}
