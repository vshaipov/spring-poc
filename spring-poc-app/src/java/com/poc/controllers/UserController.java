package com.poc.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.db.nosql.dao.UserDao;
import com.poc.db.nosql.documents.User;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDao userDao;

    @Autowired
    ObjectMapper objectMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> getUser(@RequestParam String userName)
	    throws JsonProcessingException {
	User user = userDao.getUser(userName);

	if (user != null) {
	    return new ResponseEntity<String>(objectMapper
		    .writerWithDefaultPrettyPrinter().writeValueAsString(user),
		    HttpStatus.OK);
	}
	return new ResponseEntity<String>("user not found", HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<String> getAllUsers() throws JsonProcessingException {
	
	userDao.getAllUsers().forEach((u) -> System.out.println(u.getUser()));
	
	return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public ResponseEntity<String> insertUser(@RequestParam String userName,
	    @RequestParam String password, @RequestParam String role)
	    throws JsonProcessingException {
	User user = new User(userName, role, password);
	userDao.insertUser(user);

	return new ResponseEntity<String>(objectMapper
		.writerWithDefaultPrettyPrinter().writeValueAsString(user),
		HttpStatus.OK);
    }
}
