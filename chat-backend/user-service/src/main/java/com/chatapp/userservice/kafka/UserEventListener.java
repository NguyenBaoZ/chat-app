package com.chatapp.userservice.kafka;

import com.chatapp.common.event.UserCreatedEvent;
import com.chatapp.userservice.model.User;
import com.chatapp.userservice.repository.UserRepository;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class UserEventListener {

    private final UserRepository userRepository;

    // constructor
    public UserEventListener(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @KafkaListener(topics = "user-created-topic", groupId = "user-service-group")
    public void handleUserCreated(UserCreatedEvent event) {
        System.out.println("📥 Received new user: " + event.getUsername());

        if (event == null) return;
        userRepository.findByUserIdAndIsActiveTrue(event.getUserId()).ifPresentOrElse(
                u -> {
                    System.out.println("User already exists: " + event.getUsername());
                },
                () -> {
                    User user = new User();
                    user.setUserId(event.getUserId());
                    user.setUsername(event.getUsername());
                    user.setEmail(event.getEmail());
                    user.setDisplayName(event.getUsername());
                    user.setStatus("offline");
                    user.setBio("");
                    user.setAvatar("");
                    user.setCreatedAt(Date.from(Instant.now()));
                    user.setLastActiveAt(Date.from(Instant.now()));
                    user.setActive(true);
                    userRepository.save(user);
                }
        );
    }
}
