package com.chatapp.userservice.service;

import com.chatapp.userservice.dto.UserDto;
import com.chatapp.userservice.model.Contact;
import com.chatapp.userservice.model.FriendRequest;
import com.chatapp.userservice.dto.ContactDto;
import com.chatapp.userservice.dto.FriendRequestDto;

import java.util.List;

public interface UserService {
    UserDto getUserByUserId(String userId);
    void deactivateUser(String userId);
    List<ContactDto> getContacts(String userId);
    Contact addContact(String ownerId, String friendId, String nickname);
    void deleteContact(String contactId);
    List<FriendRequestDto> getFriendRequests(String toUserId);
    FriendRequest sendFriendRequest(String fromUserId, String toUserId, String message);
    void deleteFriendRequest(String requestId);
    void acceptFriendRequest(String requestId, String accepterId);
    List<UserDto> searchUsers(String keyword, String currentUserId);
}
