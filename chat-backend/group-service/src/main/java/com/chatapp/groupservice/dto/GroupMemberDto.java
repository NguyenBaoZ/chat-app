package com.chatapp.groupservice.dto;

import java.time.Instant;

public class GroupMemberDto {
    private String membershipId;
    private String userId;
    private String role;
    private Instant joinedAt;
    private String roomId;
    private boolean isActive;

    public GroupMemberDto() {}

    public GroupMemberDto(String membershipId, String userId, String role, Instant joinedAt, String roomId, boolean isActive) {
        this.membershipId = membershipId;
        this.userId = userId;
        this.role = role;
        this.joinedAt = joinedAt;
        this.roomId = roomId;
        this.isActive = isActive;
    }

    public String getMembershipId() {
        return membershipId;    
    }

    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Instant getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Instant joinedAt) {
        this.joinedAt = joinedAt;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
