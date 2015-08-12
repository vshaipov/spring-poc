package com.poc.db.nosql.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.poc.db.nosql.documents.User;

@Service
public class UserDao {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<User> getAllUsers() {
	return mongoTemplate.findAll(User.class);
    }

    public User getUser(String user) {
	return mongoTemplate.findOne(
		new Query(Criteria.where("user").is(user)), User.class);
    }

    public void insertUser(User user) {
	mongoTemplate.save(user, "names");
    }

    // @Autowired
    // MongoOperations mongo;
    //
    // public User getUser(String user) {
    // return mongo.findOne(new Query(Criteria.where("user").is(user)),
    // User.class);
    // }
    //
    // public void insertUser(User user) {
    // mongo.save(user, "names");
    // }
}
