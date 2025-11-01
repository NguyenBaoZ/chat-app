package com.chatapp.groupservice.dto;

public class InviteMemberRequest {
    private String roomId;
    private String inviteeId;

    public String getRoomId() {
        return roomId;
    }
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public String getInviteeId() {
        return inviteeId;
    }
    public void setInviteeId(String inviteeId) {
        this.inviteeId = inviteeId;
    }
}