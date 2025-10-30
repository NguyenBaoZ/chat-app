package com.chatapp.userservice.dto;

import java.util.Date;

public class FriendRequestDto {
    private String requestId;
    private String fromUserId;
    private String toUserId;
    private String status;
    private Date createdAt;
    private boolean isActive;

    public FriendRequestDto() {}

    public FriendRequestDto(String requestId, String fromUserId, String toUserId, String status, Date createdAt, boolean isActive) {
        this.requestId = requestId;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.status = status;
        this.createdAt = createdAt;
        this.isActive = isActive;
    }

    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }

    public String getFromUserId() { return fromUserId; }
    public void setFromUserId(String fromUserId) { this.fromUserId = fromUserId; }

    public String getToUserId() { return toUserId; }
    public void setToUserId(String toUserId) { this.toUserId = toUserId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean isActive) { this.isActive = isActive; }
}
