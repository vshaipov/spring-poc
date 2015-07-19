package com.poc.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lib")
public class BookLibraryController {

	@RequestMapping(value = "/books", method = RequestMethod.GET, produces = "application/json")
	public String getBooks() {
		return "hello world";
	}
	
	@RequestMapping(params = {"bookId"}, method = RequestMethod.GET, produces = "application/json")
	public String geSpecifictBook(String bookId) {
		return "hello world " + bookId;
	}
	
	@RequestMapping(value = "/ping", method = RequestMethod.GET, produces = "application/json")
	public String ping() {
		return "ping-ping";
	}

}
