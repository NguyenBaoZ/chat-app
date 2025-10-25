package com.chatapp.authservice.repository.custom;

import com.chatapp.authservice.model.User;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    private MongoClient mongoClient;

    @Override
    public Optional<User> findByUserIdCustom(String userId) {
        MongoDatabase db = mongoClient.getDatabase("chat_auth");
        MongoCollection<Document> collection = db.getCollection("users");

        Document doc = collection.find(new Document("userId", userId)).first();

        if (doc == null) {
            return Optional.empty();
        }

        User user = new User();
        user.setUserId(doc.getString("userId"));
        user.setUsername(doc.getString("username"));
        user.setEmail(doc.getString("email"));
        user.setPassword(doc.getString("password"));
        user.setRoles((java.util.List<String>) doc.get("roles"));
        user.setActive(doc.getBoolean("isActive"));
        user.setCreatedAt(doc.get("createdAt", java.time.Instant.class));
        user.setUpdatedAt(doc.get("updatedAt", java.time.Instant.class));
        return Optional.of(user);
    }
}
