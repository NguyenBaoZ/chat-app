package com.chatapp.userservice.mapper;

import com.chatapp.userservice.dto.FriendRequestDto;
import com.chatapp.userservice.model.FriendRequest;
import org.springframework.stereotype.Component;

@Component
public class FriendRequestMapper {

    public FriendRequestDto toDto(FriendRequest fr) {
        if (fr == null) return null;
        return new FriendRequestDto(
                fr.getId(),
                fr.getFromUserId(),  
                fr.getToUserId(),   
                fr.getStatus(),
                fr.getCreatedAt()
        );
    }

    public FriendRequest toEntity(FriendRequestDto dto) {
        if (dto == null) return null;
        FriendRequest fr = new FriendRequest();
        fr.setId(dto.getId());
        fr.setFromUserId(dto.getFromUserId());
        fr.setToUserId(dto.getToUserId());
        fr.setStatus(dto.getStatus());
        fr.setCreatedAt(dto.getCreatedAt());
        return fr;
    }
}
