package com.chatapp.authservice.service;

import com.chatapp.authservice.dto.AuthResponse;
import com.chatapp.authservice.dto.LoginRequest;
import com.chatapp.authservice.dto.RegisterRequest;
import com.chatapp.commonevent.UserCreatedEvent;
import com.chatapp.authservice.model.RefreshToken;
import com.chatapp.authservice.model.User;
import com.chatapp.authservice.repository.RefreshTokenRepository;
import com.chatapp.authservice.repository.UserRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;

    //constructors
    public AuthService(UserRepository userRepository, RefreshTokenRepository refreshTokenRepository, KafkaTemplate<String, UserCreatedEvent> kafkaTemplate) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public AuthResponse register(RegisterRequest request) {
        Optional<User> existing = userRepository.findByUsername(request.getUsername());
        if (existing.isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUserId(generateUserId());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRoles(List.of("USER"));
        user.setActive(true);
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());

        userRepository.save(user);

        UserCreatedEvent event = new UserCreatedEvent(
                user.getUserId(),
                user.getUsername(),
                user.getEmail()
        );
        System.out.println("Sending Kafka message: " + event);
        kafkaTemplate.send("user-created-topic", event);

        RefreshToken refresh = new RefreshToken();
        refresh.setTokenId(generateRefreshTokenId());
        refresh.setUserId(user.getUserId());
        refresh.setToken(UUID.randomUUID().toString());
        refresh.setCreatedAt(Instant.now());
        refresh.setExpiresAt(Instant.now().plusSeconds(7 * 24 * 60 * 60)); // 7 ngày

        refreshTokenRepository.save(refresh);

        return new AuthResponse(UUID.randomUUID().toString(), refresh.getToken());
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        RefreshToken refresh = new RefreshToken();
        refresh.setTokenId(generateRefreshTokenId());
        refresh.setUserId(user.getUserId());
        refresh.setToken(UUID.randomUUID().toString());
        refresh.setCreatedAt(Instant.now());
        refresh.setExpiresAt(Instant.now().plusSeconds(7 * 24 * 60 * 60));

        refreshTokenRepository.save(refresh);

        return new AuthResponse(UUID.randomUUID().toString(), refresh.getToken());
    }

    public AuthResponse refreshToken(String token) {
        RefreshToken refresh = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (refresh.getExpiresAt().isBefore(Instant.now())) {
            throw new RuntimeException("Refresh token expired");
        }

        return new AuthResponse(UUID.randomUUID().toString(), refresh.getToken());
    }

    private String generateUserId() {
        long count = userRepository.count() + 1;
        return String.format("U%04d", count);
    }

    private String generateRefreshTokenId() {
        long count = refreshTokenRepository.count() + 1;
        return String.format("RT%04d", count);
    }
}
