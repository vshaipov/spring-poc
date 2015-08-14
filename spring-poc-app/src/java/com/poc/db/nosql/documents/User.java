package com.poc.db.nosql.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
    @Id
    private String id;
    private String user;
    private String role;
    private String password;

    @PersistenceConstructor
    public User(String user, String role, String password) {
	this.user = user;
	this.role = role;
	this.password = password;
    }
    
    @PersistenceConstructor
    public User() {
    }

    public String getId() {
	return id;
    }

    public String getUser() {
	return user;
    }

    public String getRole() {
	return role;
    }

    public String getPassword() {
	return password;
    }

}
