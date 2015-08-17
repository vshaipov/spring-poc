package com.poc.db.nosql.dao;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.poc.db.nosql.documents.User;

@Service
public class UserDao {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<User> getAllUsers() {
	return mongoTemplate.findAll(User.class, "names");
    }

    public List<User> getUser(String user) {
	return mongoTemplate.find(new Query(where("user").is(user)),
		User.class, "names");
    }

    public void insertUser(User user) {
	mongoTemplate.save(user, "names");
    }

}
