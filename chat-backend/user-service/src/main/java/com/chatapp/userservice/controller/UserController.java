package com.chatapp.userservice.controller;

import com.chatapp.userservice.dto.UserDto;
import com.chatapp.userservice.dto.ContactDto;
import com.chatapp.userservice.dto.FriendRequestDto;
import com.chatapp.userservice.exception.ResourceNotFoundException;
import com.chatapp.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{userId}/contacts")
    public ResponseEntity<List<ContactDto>> getContacts(@PathVariable String userId) {
        List<ContactDto> contacts = userService.getContacts(userId);
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/{userId}/friend-requests")
    public ResponseEntity<List<FriendRequestDto>> getFriendRequests(@PathVariable String userId) {
        List<FriendRequestDto> requests = userService.getFriendRequests(userId);
        return ResponseEntity.ok(requests);
    }

    @PostMapping("/{userId}/contacts")
    public ResponseEntity<Void> addContact(@PathVariable String userId,
                                           @RequestParam String friendId,
                                           @RequestParam String nickname) {
        userService.addContact(userId, friendId, nickname);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/friend-requests")
    public ResponseEntity<Void> sendFriendRequest(@PathVariable String userId,
                                                  @RequestParam String toUserId) {
        userService.sendFriendRequest(userId, toUserId);
        return ResponseEntity.ok().build();
    }
}
