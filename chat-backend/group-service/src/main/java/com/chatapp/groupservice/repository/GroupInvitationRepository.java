package com.chatapp.groupservice.repository;

import com.chatapp.groupservice.model.GroupInvitation;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface GroupInvitationRepository extends MongoRepository<GroupInvitation, ObjectId> {
    List<GroupInvitation> findByInvitationIdAndIsActiveTrue(String invitationId);
    Optional<GroupInvitation> findByRoomIdAndIsActiveTrue(String roomId);
}
