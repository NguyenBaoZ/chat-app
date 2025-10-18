package com.chatapp.userservice.repository;

import com.chatapp.userservice.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ContactRepository extends MongoRepository<Contact, String> {
    List<Contact> findByOwnerId(String ownerId);
}
