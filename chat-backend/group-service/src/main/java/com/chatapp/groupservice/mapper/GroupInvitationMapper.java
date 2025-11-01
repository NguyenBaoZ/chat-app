package com.chatapp.groupservice.mapper;

import com.chatapp.groupservice.dto.GroupInvitationDto;
import com.chatapp.groupservice.model.GroupInvitation;
import org.springframework.stereotype.Component;

@Component
public class GroupInvitationMapper {

    public GroupInvitationDto toDto(GroupInvitation groupInvitation) {
        if (groupInvitation == null) return null;
        return new GroupInvitationDto(
                groupInvitation.getInvitationId(),
                groupInvitation.getInviterId(),
                groupInvitation.getInviteeId(),
                groupInvitation.getStatus(),
                groupInvitation.getCreatedAt(),
                groupInvitation.getRoomId(),
                groupInvitation.isActive()
        );
    }

    public GroupInvitation toEntity(GroupInvitationDto dto) {
        if (dto == null) return null;
        GroupInvitation groupInvitation = new GroupInvitation();
        groupInvitation.setInvitationId(dto.getInvitationId());
        groupInvitation.setInviterId(dto.getInviterId());
        groupInvitation.setInviteeId(dto.getInviteeId());
        groupInvitation.setStatus(dto.getStatus());
        groupInvitation.setCreatedAt(dto.getCreatedAt());
        groupInvitation.setRoomId(dto.getRoomId());
        groupInvitation.setActive(dto.isActive());
        return groupInvitation;
    }
}
