package com.chatapp.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    private String contactId;                  

    private String ownerId;            

    private String friendId;            

    private String nickname;            

    private String status; // "accepted", "blocked"

    private Date createdAt;

    // getters and setters
    public String getContactId() {
        return contactId;
    }
    public void setContactId(String contactId) {
        this.contactId = contactId;
    }
    public String getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
    public String getFriendId() {
        return friendId;
    }
    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }   
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
