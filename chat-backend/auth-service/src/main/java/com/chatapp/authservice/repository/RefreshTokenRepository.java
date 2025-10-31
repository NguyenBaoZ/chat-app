package com.chatapp.authservice.repository;

import com.chatapp.authservice.model.RefreshToken;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends MongoRepository<RefreshToken, ObjectId> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUserId(String userId);
    List<RefreshToken> findByUserId(String userId);
}
