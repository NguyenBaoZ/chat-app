package com.chatapp.userservice.repository;

import com.chatapp.userservice.model.User;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    Optional<User> findByUserIdAndIsActiveTrue(String ownerId);
    List<User> findByUsernameContainingIgnoreCaseAndUserIdNotAndIsActiveTrue(
        String username, String userId);
    User findByUsername(String username);
}
