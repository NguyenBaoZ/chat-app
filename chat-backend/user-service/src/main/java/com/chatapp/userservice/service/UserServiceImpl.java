package com.chatapp.userservice.service;

import com.chatapp.userservice.dto.UserDto;
import com.chatapp.common.event.GroupCreatedEvent;
import com.chatapp.common.exception.ResourceNotFoundException;
import com.chatapp.userservice.dto.ContactDto;
import com.chatapp.userservice.dto.FriendRequestDto;
import com.chatapp.userservice.model.User;
import com.chatapp.userservice.model.Contact;
import com.chatapp.userservice.model.FriendRequest;
import com.chatapp.userservice.repository.UserRepository;
import com.chatapp.userservice.repository.ContactRepository;
import com.chatapp.userservice.repository.FriendRequestRepository;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ContactRepository contactRepository;
    private final FriendRequestRepository friendRequestRepository;
    private final KafkaTemplate<String, GroupCreatedEvent> kafkaTemplate;

    public UserServiceImpl(UserRepository userRepository, ContactRepository contactRepository, FriendRequestRepository friendRequestRepository, KafkaTemplate<String, GroupCreatedEvent> kafkaTemplate) {
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
        this.friendRequestRepository = friendRequestRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        User user = userRepository.findByUserIdAndIsActiveTrue(userId)
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
                user.getLastActiveAt(),
                user.isActive()
        );
    }

    // --- delete user (soft delete)
    public void deactivateUser(String userId) {
        User user = userRepository.findByUserIdAndIsActiveTrue(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (user.getId() == null) {
            throw new IllegalStateException("User _id is not existing");
        }
        if (!user.isActive()) {
            throw new IllegalStateException("User is already deactivated");
        }

        user.setActive(false);
        user.setLastActiveAt(Date.from(Instant.now()));
        userRepository.save(user);
    }

    @Override
    public List<ContactDto> getContacts(String ownerId) {
        return contactRepository.findByOwnerIdAndIsActiveTrue(ownerId)
                .stream()
                .map(c -> new ContactDto(
                        c.getContactId(),
                        c.getOwnerId(),
                        c.getFriendId(),
                        c.getNickname(),
                        c.getStatus(),
                        c.isActive()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<FriendRequestDto> getFriendRequests(String toUserId) {
        return friendRequestRepository.findByToUserIdAndIsActiveTrue(toUserId)
                .stream()
                .map(c -> new FriendRequestDto(
                        c.getRequestId(),
                        c.getFromUserId(),
                        c.getToUserId(),
                        c.getStatus(),
                        c.getCreatedAt(),
                        c.isActive()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Contact addContact(String ownerId, String friendId, String nickname) {
        // check duplicates
        Optional<Contact> exists = contactRepository.findByOwnerIdAndFriendId(ownerId, friendId);
        if (exists.isPresent()) {
            Contact c = exists.get();
            if (!c.isActive()) { c.setActive(true); }
            c.setNickname(nickname);
            return contactRepository.save(c);
        }
        Contact contact = new Contact();
        contact.setContactId(generateContactId());
        contact.setOwnerId(ownerId);
        contact.setFriendId(friendId);
        contact.setNickname(nickname);
        contact.setStatus("accepted");
        contact.setCreatedAt(Date.from(Instant.now()));
        contact.setActive(true);
        return contactRepository.save(contact);
    }

    @Override
    public void deleteContact(String contactId) {
        contactRepository.findByContactId(contactId).ifPresent(c -> {
            c.setActive(false);
            contactRepository.save(c);
        });
    }

    @Override
    public FriendRequest sendFriendRequest(String fromUserId, String toUserId, String message) {
        FriendRequest request = new FriendRequest();
        request.setRequestId(generateFriendRequestId());
        request.setFromUserId(fromUserId);
        request.setToUserId(toUserId);
        request.setStatus("pending");
        request.setCreatedAt(Date.from(Instant.now()));
        request.setActive(true);
        return friendRequestRepository.save(request);
    }

    public void deleteFriendRequest(String requestId) {
        friendRequestRepository.findByRequestId(requestId).ifPresent(fr -> {
            fr.setActive(false);
            friendRequestRepository.save(fr);
        });
    }

    @Transactional
    public void acceptFriendRequest(String requestId, String accepterId) {
        FriendRequest fr = friendRequestRepository.findByRequestId(requestId)
                .orElseThrow(() -> new RuntimeException("Friend request not found"));
        if (!fr.getToUserId().equals(accepterId)) {
            throw new RuntimeException("Not authorized to accept this request");
        }
        
        System.out.println("Accepting friend request: " + fr);
        System.out.println("From userId: " + fr.getFromUserId() + ", To userId: " + fr.getToUserId());
        User fromUser = userRepository.findByUserIdAndIsActiveTrue(fr.getFromUserId())
        .orElseThrow(() -> new RuntimeException("From user not found"));
        User toUser = userRepository.findByUserIdAndIsActiveTrue(fr.getToUserId())
        .orElseThrow(() -> new RuntimeException("To user not found"));
        
        fr.setStatus("accepted");
        friendRequestRepository.save(fr);
        // create mutual contacts
        addContact(fr.getFromUserId(), fr.getToUserId(), fromUser.getDisplayName());
        addContact(fr.getToUserId(), fr.getFromUserId(), toUser.getDisplayName());

        // publish event to Kafka for group service to create 1-on-1 group
        GroupCreatedEvent event = new GroupCreatedEvent(
                generateRoomId(),
                fr.getFromUserId(),
                fr.getToUserId(),
                fromUser.getUsername(),
                toUser.getUsername(),
                Instant.now()
        );

        System.out.println("Sending Kafka message: " + event);
        kafkaTemplate.send("group-created-topic", event);
    }

    public List<UserDto> searchUsers(String keyword, String currentUserId) {
        return userRepository
            .findByUsernameContainingIgnoreCaseAndUserIdNotAndIsActiveTrue(keyword, currentUserId)
            .stream()
            .map(user -> new UserDto(
                        user.getUserId(),
                        user.getUsername(),
                        user.getDisplayName(),
                        user.getEmail(),
                        user.getAvatar(),
                        user.getStatus(),
                        user.getBio(),
                        user.getCreatedAt(),
                        user.getLastActiveAt(),
                        user.isActive()
                ))
            .collect(Collectors.toList());
    }

    private String generateContactId() {
        return "C" + UUID.randomUUID().toString().substring(0, 8);
    }

    private String generateFriendRequestId() {
        return "FR" + UUID.randomUUID().toString().substring(0, 8);
    }

    private String generateRoomId() {
        return "G" + UUID.randomUUID().toString().substring(0, 8);
    }
}
