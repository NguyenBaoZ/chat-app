package com.chatapp.common.event;

import java.time.Instant;

public class FriendAcceptedEvent {
    private String requesterId;
    private String accepterId;
    private Instant acceptedAt;

    public FriendAcceptedEvent() {}
    public FriendAcceptedEvent(String requesterId, String accepterId, Instant acceptedAt) {
        this.requesterId = requesterId; this.accepterId = accepterId; this.acceptedAt = acceptedAt;
    }
    // getters/setters
    public String getRequesterId(){return requesterId;}
    public void setRequesterId(String requesterId){this.requesterId=requesterId;}
    public String getAccepterId(){return accepterId;}
    public void setAccepterId(String accepterId){this.accepterId=accepterId;}
    public Instant getAcceptedAt(){return acceptedAt;}
    public void setAcceptedAt(Instant acceptedAt){this.acceptedAt=acceptedAt;}
}
