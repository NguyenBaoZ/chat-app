package com.chatapp.userservice.repository;

import com.chatapp.userservice.model.FriendRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FriendRequestRepository extends MongoRepository<FriendRequest, String> {
    List<FriendRequest> findByToUserIdAndStatus(String toUserId, String status);
}
