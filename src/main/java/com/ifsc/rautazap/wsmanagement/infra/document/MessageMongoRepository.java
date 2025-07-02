package com.ifsc.rautazap.wsmanagement.infra.document;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageMongoRepository extends MongoRepository<MessageDocument, String> {

    List<MessageDocument> findByToUserIdAndDeliveredFalseOrderByTimestampDesc(String toUserId);
}
