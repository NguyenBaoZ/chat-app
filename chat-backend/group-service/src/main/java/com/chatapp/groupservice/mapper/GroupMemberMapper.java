package com.chatapp.groupservice.mapper;

import com.chatapp.groupservice.dto.GroupMemberDto;
import com.chatapp.groupservice.model.GroupMember;
import org.springframework.stereotype.Component;

@Component
public class GroupMemberMapper {

    public GroupMemberDto toDto(GroupMember groupMember) {
        if (groupMember == null) return null;
        return new GroupMemberDto(
                groupMember.getMembershipId(),
                groupMember.getUserId(),
                groupMember.getRole(),
                groupMember.getJoinedAt(),
                groupMember.getRoomId(),
                groupMember.isActive()
        );
    }

    public GroupMember toEntity(GroupMemberDto dto) {
        if (dto == null) return null;
        GroupMember groupMember = new GroupMember();
        groupMember.setMembershipId(dto.getMembershipId());
        groupMember.setUserId(dto.getUserId());
        groupMember.setRole(dto.getRole());
        groupMember.setJoinedAt(dto.getJoinedAt());
        groupMember.setRoomId(dto.getRoomId());
        groupMember.setActive(dto.isActive());
        return groupMember;
    }
}
