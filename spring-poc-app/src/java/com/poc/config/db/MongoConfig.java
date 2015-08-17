package com.poc.config.db;

import java.io.IOException;
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
import com.poc.db.nosql.documents.Grade;
import com.poc.db.nosql.documents.User;

@Configuration
@EnableMongoRepositories(basePackages = "com.poc.db")
public class MongoConfig extends AbstractMongoConfiguration {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    protected String getDatabaseName() {
	return "test";
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
	return new Mongo("localhost");
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
	obtainUsers(mongoTemplate);
	obtainGrades(mongoTemplate);
    }

    private void obtainUsers(MongoTemplate mongoTemplate) throws IOException,
	    JsonParseException, JsonMappingException {
	List<User> list = objectMapper.readValue(
		IOUtils.toString(getClass().getClassLoader()
			.getResourceAsStream("db/users.json")),
		TypeFactory.defaultInstance().constructCollectionType(
			List.class, User.class));

	list.forEach((user) -> mongoTemplate.save(
		new User(user.getUser(), user.getRole(), user.getPassword()),
		"names"));
    }

    private void obtainGrades(MongoTemplate mongoTemplate) throws IOException,
	    JsonParseException, JsonMappingException {
	List<Grade> list = objectMapper.readValue(
		IOUtils.toString(getClass().getClassLoader()
			.getResourceAsStream("db/grades.json")),
		TypeFactory.defaultInstance().constructCollectionType(
			List.class, Grade.class));

	list.forEach((grade) -> mongoTemplate.save(
		new Grade(grade.getStudent_id(), grade.getType(), grade
			.getScore()), "grades"));
    }

}