package com.chatapp.groupservice.repository;

import com.chatapp.groupservice.model.Group;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends MongoRepository<Group, ObjectId> {
    List<Group> findByOwnerIdAndIsActiveTrue(String ownerId);
    Optional<Group> findByRoomIdAndIsActiveTrue(String roomId);
    List<Group> findAllByRoomIdInAndIsActiveTrue(List<String> roomIds);
}
