package com.chatapp.common.event;

import java.time.Instant;

public class GroupCreatedEvent {
    private String roomId;
    private String requesterId;
    private String accepterId;
    private String fromUserName;
    private String toUserName;
    private Instant acceptedAt;

    public GroupCreatedEvent() {}
    public GroupCreatedEvent(String roomId, String requesterId, String accepterId, String fromUserName, String toUserName, Instant acceptedAt) {
        this.roomId = roomId;
        this.requesterId = requesterId;
        this.accepterId = accepterId;
        this.fromUserName = fromUserName;
        this.toUserName = toUserName;
        this.acceptedAt = acceptedAt;
    }
    // getters/setters
    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }
    public String getRequesterId(){return requesterId;}
    public void setRequesterId(String requesterId){this.requesterId=requesterId;}
    public String getAccepterId(){return accepterId;}
    public void setAccepterId(String accepterId){this.accepterId=accepterId;}
    public Instant getAcceptedAt(){return acceptedAt;}
    public void setAcceptedAt(Instant acceptedAt){this.acceptedAt=acceptedAt;}
    public String getFromUserName() { return fromUserName; }
    public void setFromUserName(String fromUserName) { this.fromUserName = fromUserName; }
    public String getToUserName() { return toUserName; }
    public void setToUserName(String toUserName) { this.toUserName = toUserName; }
}
