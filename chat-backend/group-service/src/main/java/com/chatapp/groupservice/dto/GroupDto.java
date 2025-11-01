package com.chatapp.groupservice.dto;

import java.time.Instant;

public class GroupDto {
    private String name;
    private String roomId;
    private String ownerId;
    private String avatar;
    private String description;
    private String roomType;
    private Instant createdAt;
    private boolean isActive;


    public GroupDto() {}

    public GroupDto(String name, String ownerId, String avatar, String description,String roomType, Instant createdAt, String roomId, boolean isActive) {
        this.name = name;
        this.ownerId = ownerId;
        this.avatar = avatar;
        this.description = description;
        this.roomType = roomType;
        this.createdAt = createdAt;
        this.roomId = roomId;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRoomId() {
        return roomId;
    }
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public String getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getRoomType() {
        return roomType;
    }
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
