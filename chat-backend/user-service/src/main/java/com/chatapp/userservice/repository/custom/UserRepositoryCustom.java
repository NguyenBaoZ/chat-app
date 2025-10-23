package com.chatapp.userservice.repository.custom;

import com.chatapp.userservice.model.User;
import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<User> findByUserIdCustom(String userId);
}
