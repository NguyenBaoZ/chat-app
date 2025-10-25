package com.chatapp.authservice.repository.custom;

import com.chatapp.authservice.model.User;
import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<User> findByUserIdCustom(String userId);
}
