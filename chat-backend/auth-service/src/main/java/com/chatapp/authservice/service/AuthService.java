package com.chatapp.authservice.service;

import com.chatapp.authservice.dto.AuthResponse;
import com.chatapp.authservice.dto.LoginRequest;
import com.chatapp.authservice.dto.RegisterRequest;
import com.chatapp.common.event.UserCreatedEvent;
import com.chatapp.authservice.model.RefreshToken;
import com.chatapp.authservice.model.User;
import com.chatapp.authservice.repository.RefreshTokenRepository;
import com.chatapp.authservice.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    //constructors
    public AuthService(UserRepository userRepository, RefreshTokenRepository refreshTokenRepository, KafkaTemplate<String, UserCreatedEvent> kafkaTemplate, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;   
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
        user.setPassword(passwordEncoder.encode(request.getPassword()));
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

        String accessToken = jwtService.generateToken(user.getUserId());
        RefreshToken refreshToken = createRefreshToken(user);

        return new AuthResponse(accessToken, refreshToken.getToken());
    }

    public boolean validatePassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username"));

        if (!validatePassword(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        refreshTokenRepository.deleteByUserId(user.getUserId());

        String accessToken = jwtService.generateToken(user.getUserId());
        RefreshToken refreshToken = createRefreshToken(user);

        return new AuthResponse(accessToken, refreshToken.getToken());
    }

    public AuthResponse refreshToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (refreshToken.getExpiresAt().isBefore(Instant.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("Refresh token expired");
        }

        refreshTokenRepository.delete(refreshToken);
        String newAccessToken = jwtService.generateToken(refreshToken.getUserId());
        return new AuthResponse(newAccessToken, refreshToken.getToken());
    }

    public void logout(String userId) {
        refreshTokenRepository.deleteByUserId(userId);
    }

    private String generateUserId() {
        return "U" + UUID.randomUUID().toString().substring(0, 8);
    }

    private String generateRefreshTokenId() {
        return "RT" + UUID.randomUUID().toString().substring(0, 8);
    }

    private RefreshToken createRefreshToken(User user) {
        RefreshToken refresh = new RefreshToken();
        refresh.setTokenId(generateRefreshTokenId());
        refresh.setUserId(user.getUserId());
        refresh.setToken(UUID.randomUUID().toString());
        refresh.setCreatedAt(Instant.now());
        refresh.setExpiresAt(Instant.now().plusSeconds(7 * 24 * 60 * 60));

        return refreshTokenRepository.save(refresh);
    }
}
