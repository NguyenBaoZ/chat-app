package com.chatapp.userservice.repository;

import com.chatapp.userservice.repository.custom.UserRepositoryCustom;
import com.chatapp.userservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom {
    User findByUsername(String username);
}
