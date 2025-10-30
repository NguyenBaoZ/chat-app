package com.chatapp.userservice.repository;

import com.chatapp.userservice.model.FriendRequest;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRequestRepository extends MongoRepository<FriendRequest, ObjectId> {
    List<FriendRequest> findByToUserIdAndIsActiveTrue(String toUserId);
    Optional<FriendRequest> findByRequestId(String requestId);
    List<FriendRequest> findByFromUserIdAndIsActiveTrue(String fromUserId);
}
