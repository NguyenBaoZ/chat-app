package com.chatapp.userservice.repository;

import com.chatapp.userservice.model.Contact;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends MongoRepository<Contact, ObjectId> {
    List<Contact> findByOwnerIdAndIsActiveTrue(String ownerId);
    Optional<Contact> findByContactId(String contactId);
    Optional<Contact> findByOwnerIdAndFriendId(String ownerId, String friendId);
}
