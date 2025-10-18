package com.chatapp.userservice.service;

import com.chatapp.userservice.dto.UserDto;
import com.chatapp.userservice.dto.ContactDto;
import com.chatapp.userservice.dto.FriendRequestDto;

import java.util.List;

public interface UserService {
    UserDto getUserById(String id);
    List<ContactDto> getContacts(String userId);
    List<FriendRequestDto> getFriendRequests(String userId);
    void addContact(String ownerId, String friendId, String nickname);
    void sendFriendRequest(String fromUser, String toUser);
}
