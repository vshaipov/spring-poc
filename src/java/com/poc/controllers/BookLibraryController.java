package com.poc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poc.services.api.LibraryService;

@RestController
@RequestMapping("/lib")
public class BookLibraryController {

	@Autowired
	private LibraryService libraryService;

	@RequestMapping(value = "/books", method = RequestMethod.GET, produces = "application/json")
	public String getBooks() {
		return libraryService.getBooks();
	}

	@RequestMapping(params = { "bookId" }, method = RequestMethod.GET, produces = "application/json")
	public String geSpecifictBook(String bookId) {
		return libraryService.getSpecifictBook(bookId);
	}

	@RequestMapping(value = "/ping", method = RequestMethod.GET, produces = "application/json")
	public String ping() {
		return "ping-ping";
	}

	public LibraryService getLibraryService() {
		return libraryService;
	}

	public void setLibraryService(LibraryService libraryService) {
		this.libraryService = libraryService;
	}
}
