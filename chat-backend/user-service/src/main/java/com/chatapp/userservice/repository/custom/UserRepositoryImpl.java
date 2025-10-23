package com.chatapp.userservice.repository.custom;

import com.chatapp.userservice.model.User;
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
        MongoDatabase db = mongoClient.getDatabase("chat_user");
        MongoCollection<Document> collection = db.getCollection("users");

        Document doc = collection.find(new Document("userId", userId)).first();

        if (doc == null) {
            return Optional.empty();
        }

        User user = new User();
        user.setUserId(doc.getString("userId"));
        user.setUsername(doc.getString("username"));
        user.setDisplayName(doc.getString("displayName"));
        user.setEmail(doc.getString("email"));
        user.setStatus(doc.getString("status"));
        user.setBio(doc.getString("bio"));
        return Optional.of(user);
    }
}
