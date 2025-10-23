package com.chatapp.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Indexed(unique = true)
    private String userId;                   
    
    private String username;             

    private String displayName;          

    private String email;                

    private String avatar;              

    private String status;               

    private String bio;                  

    private Date createdAt;              

    private Date lastActiveAt;           

    // getters and setters
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }
    public Date getCreatedAt() {
        return createdAt;
    }   
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public Date getLastActiveAt() {
        return lastActiveAt;
    }
    public void setLastActiveAt(Date lastActiveAt) {
        this.lastActiveAt = lastActiveAt;
    }
}
