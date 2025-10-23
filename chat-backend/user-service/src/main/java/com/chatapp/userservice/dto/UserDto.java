package com.chatapp.userservice.dto;

import java.util.Date;

public class UserDto {
    private String userId;
    private String username;
    private String displayName;
    private String email;
    private String avatar;
    private String status;
    private String bio;
    private Date createdAt;
    private Date lastActiveAt;

    public UserDto() {}

    public UserDto(String userId, String username, String displayName, String email,
                   String avatar, String status, String bio, Date createdAt, Date lastActiveAt) {
        this.userId = userId;
        this.username = username;
        this.displayName = displayName;
        this.email = email;
        this.avatar = avatar;
        this.status = status;
        this.bio = bio;
        this.createdAt = createdAt;
        this.lastActiveAt = lastActiveAt;
    }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getLastActiveAt() { return lastActiveAt; }
    public void setLastActiveAt(Date lastActiveAt) { this.lastActiveAt = lastActiveAt; }
}
