package com.poc.config.db;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mongodb.BasicDBObject;
import com.mongodb.Mongo;
import com.poc.db.nosql.documents.User;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Configuration
@EnableMongoRepositories(basePackages = "com.poc.db")
public class MongoConfig extends AbstractMongoConfiguration {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    protected String getDatabaseName() {
	return "test";
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
	MongoTemplate mongoTemplate = new MongoTemplate(mongo(),
		getDatabaseName());
	cleanUpDb(mongoTemplate);
	initDb(mongoTemplate);
	return mongoTemplate;
    }

    private void cleanUpDb(MongoTemplate mongoTemplate) {
	for (String collectionName : mongoTemplate.getCollectionNames()) {
	    if (!collectionName.startsWith("system.")) {
		mongoTemplate.getCollection(collectionName).remove(
			new BasicDBObject());
	    }
	}
    }

    private void initDb(MongoTemplate mongoTemplate) throws JsonParseException,
	    JsonMappingException, IOException {
	ClassLoader classLoader = getClass().getClassLoader();

	List<User> list = objectMapper
		.readValue(
			IOUtils.toString(classLoader
				.getResourceAsStream("users.json")),
			TypeFactory.defaultInstance().constructCollectionType(
				List.class, User.class));

	for (User user : list) {
	    mongoTemplate.save(new User(user.getUser(), user.getRole(), user.getPassword()), "names");
	}

    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
	return new Mongo("localhost");
    }

}