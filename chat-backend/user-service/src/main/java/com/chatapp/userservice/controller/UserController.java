package com.chatapp.userservice.controller;

import com.chatapp.userservice.dto.UserDto;
import com.chatapp.userservice.dto.ContactDto;
import com.chatapp.userservice.dto.FriendRequestDto;
import com.chatapp.userservice.exception.ResourceNotFoundException;
import com.chatapp.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable String id) {
        UserDto dto = userService.getUserById(id);
        if (dto == null) throw new ResourceNotFoundException("User not found: " + id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}/contacts")
    public ResponseEntity<List<ContactDto>> getContacts(@PathVariable String id) {
        List<ContactDto> contacts = userService.getContacts(id);
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/{id}/friend-requests")
    public ResponseEntity<List<FriendRequestDto>> getFriendRequests(@PathVariable String id) {
        List<FriendRequestDto> requests = userService.getFriendRequests(id);
        return ResponseEntity.ok(requests);
    }

    @PostMapping("/{id}/contacts")
    public ResponseEntity<Void> addContact(@PathVariable String id,
                                           @RequestParam String friendId,
                                           @RequestParam String nickname) {
        userService.addContact(id, friendId, nickname);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/friend-requests")
    public ResponseEntity<Void> sendFriendRequest(@PathVariable String id,
                                                  @RequestParam String toUserId) {
        userService.sendFriendRequest(id, toUserId);
        return ResponseEntity.ok().build();
    }
}
