package com.chatapp.groupservice.repository;

import com.chatapp.groupservice.model.GroupMember;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface GroupMemberRepository extends MongoRepository<GroupMember, ObjectId> {
    Optional<GroupMember> findByUserIdAndRoomIdAndIsActiveTrue(String userId, String roomId);
    List<GroupMember> findByRoomIdAndIsActiveTrue(String roomId);
    Optional<GroupMember> findByUserIdAndIsActiveTrue(String userId);
}
