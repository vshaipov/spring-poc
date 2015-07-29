package com.poc.services.impl;

import org.springframework.stereotype.Service;

import com.poc.services.api.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService{

	@Override
	public String getBooks() {
		return "hello world";
	}

	@Override
	public String getSpecifictBook(String bookId) {
		return "hello world " + bookId;
	}

}
