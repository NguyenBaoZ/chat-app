package com.chatapp.groupservice.mapper;

import com.chatapp.groupservice.dto.GroupDto;
import com.chatapp.groupservice.model.Group;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper {

    public GroupDto toDto(Group group) {
        if (group == null) return null;
        return new GroupDto(
                group.getName(),
                group.getOwnerId(),
                group.getAvatar(),
                group.getDescription(),
                group.getRoomType(),
                group.getCreatedAt(),
                group.getRoomId(),
                group.isActive()
        );
    }

    public Group toEntity(GroupDto dto) {
        if (dto == null) return null;
        Group group = new Group();
        group.setName(dto.getName());
        group.setOwnerId(dto.getOwnerId());
        group.setAvatar(dto.getAvatar());
        group.setDescription(dto.getDescription());
        group.setRoomType(dto.getRoomType());
        group.setCreatedAt(dto.getCreatedAt());
        group.setRoomId(dto.getRoomId());
        group.setActive(dto.isActive());
        return group;
    }
}
