package com.chatapp.userservice.service;

import com.chatapp.userservice.dto.UserDto;
import com.chatapp.userservice.dto.ContactDto;
import com.chatapp.userservice.dto.FriendRequestDto;
import com.chatapp.userservice.model.User;
import com.chatapp.userservice.model.Contact;
import com.chatapp.userservice.model.FriendRequest;
import com.chatapp.userservice.repository.UserRepository;
import com.chatapp.userservice.repository.ContactRepository;
import com.chatapp.userservice.repository.FriendRequestRepository;
import com.chatapp.userservice.mapper.FriendRequestMapper;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ContactRepository contactRepository;
    private final FriendRequestRepository friendRequestRepository;
    private final FriendRequestMapper friendRequestMapper;

    public UserServiceImpl(UserRepository userRepository, ContactRepository contactRepository, FriendRequestRepository friendRequestRepository, FriendRequestMapper friendRequestMapper) {
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
        this.friendRequestRepository = friendRequestRepository;
        this.friendRequestMapper = friendRequestMapper;
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        User user = userRepository.findByUserIdCustom(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserDto(
                user.getUserId(),
                user.getUsername(),
                user.getDisplayName(),
                user.getEmail(),
                user.getAvatar(),
                user.getStatus(),
                user.getBio(),
                user.getCreatedAt(),
                user.getLastActiveAt()
        );
    }

    @Override
    public List<ContactDto> getContacts(String userId) {
        return contactRepository.findByOwnerId(userId)
                .stream()
                .map(c -> new ContactDto(
                        c.getContactId(),
                        c.getOwnerId(),
                        c.getFriendId(),
                        c.getNickname(),
                        c.getStatus()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<FriendRequestDto> getFriendRequests(String userId) {
        return friendRequestRepository.findByToUserIdAndStatus(userId, "pending")
                .stream()
                .map(friendRequestMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void addContact(String ownerId, String friendId, String nickname) {
        Contact contact = new Contact();
        contact.setContactId(generateContactId());
        contact.setOwnerId(ownerId);
        contact.setFriendId(friendId);
        contact.setNickname(nickname);
        contact.setStatus("accepted");
        contactRepository.save(contact);
    }

    @Override
    public void sendFriendRequest(String fromUserId, String toUserId) {
        FriendRequest request = new FriendRequest();
        request.setFromUserId(fromUserId);
        request.setToUserId(toUserId);
        request.setStatus("pending");
        request.setCreatedAt(Date.from(Instant.now()));
        friendRequestRepository.save(request);
    }

    private String generateContactId() {
        long count = contactRepository.count() + 1;
        return String.format("C%04d", count);
    }
}
