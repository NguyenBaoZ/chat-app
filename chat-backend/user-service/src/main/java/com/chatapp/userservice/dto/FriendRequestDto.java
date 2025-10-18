package com.chatapp.userservice.dto;

import java.util.Date;

public class FriendRequestDto {
    private String id;
    private String fromUserId;  // ID người gửi
    private String toUserId;    // ID người nhận
    private String status;
    private Date createdAt;

    public FriendRequestDto() {}

    public FriendRequestDto(String id, String fromUserId, String toUserId, String status, Date createdAt) {
        this.id = id;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters và Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFromUserId() { return fromUserId; }
    public void setFromUserId(String fromUserId) { this.fromUserId = fromUserId; }

    public String getToUserId() { return toUserId; }
    public void setToUserId(String toUserId) { this.toUserId = toUserId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
