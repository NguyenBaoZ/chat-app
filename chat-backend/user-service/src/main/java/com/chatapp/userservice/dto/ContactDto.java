package com.chatapp.userservice.dto;

public class ContactDto {
    private String contactId;
    private String ownerId;
    private String friendId;
    private String nickname;
    private String status;

    public ContactDto() {}

    public ContactDto(String contactId, String ownerId, String friendId, String nickname, String status) {
        this.contactId = contactId;
        this.ownerId = ownerId;
        this.friendId = friendId;
        this.nickname = nickname;
        this.status = status;
    }

    // Getters và Setters
    public String getContactId() { return contactId; }
    public void setContactId(String contactId) { this.contactId = contactId; }

    public String getOwnerId() { return ownerId; }
    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }

    public String getFriendId() { return friendId; }
    public void setFriendId(String friendId) { this.friendId = friendId; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
