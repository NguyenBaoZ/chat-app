package com.chatapp.userservice.mapper;

import com.chatapp.userservice.dto.UserDto;
import com.chatapp.userservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        if (user == null) return null;
        return new UserDto(
                user.getUserId(),
                user.getUsername(),
                user.getDisplayName(),
                user.getEmail(),
                user.getAvatar(),
                user.getStatus(),
                user.getBio(),
                user.getCreatedAt(),
                user.getLastActiveAt(),
                user.isActive()
        );
    }

    public User toEntity(UserDto dto) {
        if (dto == null) return null;
        User user = new User();
        user.setUserId(dto.getUserId());
        user.setUsername(dto.getUsername());
        user.setDisplayName(dto.getDisplayName());
        user.setEmail(dto.getEmail());
        user.setAvatar(dto.getAvatar());
        user.setStatus(dto.getStatus());
        user.setBio(dto.getBio());
        user.setCreatedAt(dto.getCreatedAt());
        user.setLastActiveAt(dto.getLastActiveAt());
        user.setActive(dto.isActive());
        return user;
    }
}
