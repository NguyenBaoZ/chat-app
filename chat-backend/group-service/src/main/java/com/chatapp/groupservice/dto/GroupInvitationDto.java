package com.chatapp.groupservice.dto;

import java.time.Instant;

public class GroupInvitationDto {
    private String invitationId;
    private String inviterId;
    private String inviteeId;
    private String status; // pending, accepted, rejected
    private Instant createdAt;
    private String roomId;
    private boolean isActive;

    public GroupInvitationDto() {}

    public GroupInvitationDto(String invitationId, String inviterId, String inviteeId, String status, Instant createdAt, String roomId, boolean isActive) {
        this.invitationId = invitationId;
        this.inviterId = inviterId;
        this.inviteeId = inviteeId;
        this.status = status;
        this.createdAt = createdAt;
        this.roomId = roomId;
        this.isActive = isActive;
    }
    
    public String getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(String invitationId) {
        this.invitationId = invitationId;
    }

    public String getInviterId() {
        return inviterId;
    }

    public void setInviterId(String inviterId) {
        this.inviterId = inviterId;
    }

    public String getInviteeId() {
        return inviteeId;
    }

    public void setInviteeId(String inviteeId) {
        this.inviteeId = inviteeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
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
