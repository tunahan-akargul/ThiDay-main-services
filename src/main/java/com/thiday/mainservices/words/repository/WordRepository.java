package com.thiday.mainservices.words.repository;

import com.thiday.mainservices.words.model.Word;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface WordRepository extends MongoRepository<Word, String> {
    Optional<Word> findByOwnerIdAndCreatedAt(String ownerId, String createdAt);
}



