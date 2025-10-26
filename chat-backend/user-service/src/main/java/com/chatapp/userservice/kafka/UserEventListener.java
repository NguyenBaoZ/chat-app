package com.chatapp.userservice.kafka;

import com.chatapp.common.event.UserCreatedEvent;
import com.chatapp.userservice.model.User;
import com.chatapp.userservice.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

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

        User user = new User();
        user.setUserId(event.getUserId());
        user.setUsername(event.getUsername());
        user.setEmail(event.getEmail());
        user.setDisplayName(event.getUsername());
        user.setStatus("offline");
        user.setCreatedAt(Date.from(Instant.now()));
        user.setLastActiveAt(Date.from(Instant.now()));
        userRepository.save(user);
    }
}
