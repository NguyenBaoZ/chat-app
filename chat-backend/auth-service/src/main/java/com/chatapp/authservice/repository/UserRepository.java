package com.chatapp.authservice.repository;

import com.chatapp.authservice.model.User;
import com.chatapp.authservice.repository.custom.UserRepositoryCustom;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
