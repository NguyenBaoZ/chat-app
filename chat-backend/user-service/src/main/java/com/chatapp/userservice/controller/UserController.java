package com.chatapp.userservice.controller;

import com.chatapp.userservice.dto.UserDto;
import com.chatapp.userservice.model.Contact;
import com.chatapp.userservice.dto.ContactDto;
import com.chatapp.userservice.dto.FriendRequestDto;
import com.chatapp.common.exception.ResourceNotFoundException;
import com.chatapp.userservice.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable String userId) {
        UserDto dto = userService.getUserByUserId(userId);
        if (dto == null) {
            throw new ResourceNotFoundException("User not found: " + userId);
        }
        return ResponseEntity.ok(dto);
    }

    // deactivate user
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deactivateUser(@PathVariable String userId) {
        userService.deactivateUser(userId);
        return ResponseEntity.noContent().build();
    }
    
    // contacts
    @GetMapping("/{userId}/contacts")
    public ResponseEntity<List<Object>> getContacts(@PathVariable String userId) {
        List<ContactDto> list = userService.getContacts(userId);
        List<Object> out = list.stream().map(c -> {
            return new Object() {
                public final String contactId = c.getContactId();
                public final String friendId = c.getFriendId();
                public final String nickname = c.getNickname();
                public final String status = c.getStatus();
            };
        }).collect(Collectors.toList());
        return ResponseEntity.ok(out);
    }

    @PostMapping("/{userId}/contacts")
    public ResponseEntity<Object> addContact(@PathVariable String userId,
                                             @RequestParam String friendId,
                                             @RequestParam(required = false) String nickname) {
        Contact c = userService.addContact(userId, friendId, nickname == null ? "" : nickname);
        return ResponseEntity.ok(new Object() {
            public final String contactId = c.getContactId();
            public final String friendId = c.getFriendId();
            public final String nickname = c.getNickname();
        });
    }

    @DeleteMapping("/contacts/{contactId}")
    public ResponseEntity<Void> deleteContact(@PathVariable String contactId) {
        userService.deleteContact(contactId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{toUserId}/friend-requests")
    public ResponseEntity<List<FriendRequestDto>> getFriendRequests(@PathVariable String toUserId) {
        List<FriendRequestDto> requests = userService.getFriendRequests(toUserId);
        return ResponseEntity.ok(requests);
    }

    @PostMapping("/{fromUserId}/friend-requests")
    public ResponseEntity<Void> sendFriendRequest(@PathVariable String fromUserId,
                                                  @RequestParam String toUserId,
                                                  @RequestParam(required = false) String message) {
        userService.sendFriendRequest(fromUserId, toUserId, message == null ? "" : message);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/friend-requests/{requestId}")
    public ResponseEntity<Void> deleteFriendRequest(@PathVariable String requestId) {
        userService.deleteFriendRequest(requestId);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/{userId}/friend-requests/{requestId}/accept")
    public ResponseEntity<Void> acceptFriend(@PathVariable String userId, @PathVariable String requestId) {
        userService.acceptFriendRequest(requestId, userId);
        return ResponseEntity.ok().build();
    }

    // 1. LẤY USER HIỆN TẠI (Profile)
    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(
            @RequestHeader("X-User-Id") String userId) {
        return ResponseEntity.ok(userService.getUserByUserId(userId));
    }

    // 3. TÌM KIẾM BẠN BÈ
    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> searchUsers(
            @RequestParam String q,
            @RequestHeader("X-User-Id") String currentUserId) {
        return ResponseEntity.ok(userService.searchUsers(q, currentUserId));
    }
}
