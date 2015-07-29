package com.poc.rest;

import org.junit.Test;

public class SecurityTest extends IntegrationTestSupport {
	// @formatter:off
	
	@Test
	public void pingAsUser_403() {
		given().header("Authorization", userBasicAuth).then()
			.statusCode(SC_FORBIDDEN).when()
		.get("/spring-poc/lib/ping");
	}
	
	@Test
	public void ping_401() {
		given().header("Authorization", incorrectBasicAuth).then()
			.statusCode(SC_UNAUTHORIZED).when()
		.get("/spring-poc/lib/ping");
	}

	@Test
	public void pingAsAdmin_200_OK() {
		given().header("Authorization", adminBasicAuth).then()
			.statusCode(SC_OK).with().when()
		.get("/spring-poc/lib/ping");
	}

	@Test
	public void getBooks_401() {
		given().header("Authorization", incorrectBasicAuth).then()
			.statusCode(SC_UNAUTHORIZED)
		.when().get("/spring-poc/lib/books");
	}
	
	@Test
	public void getBooksAsUserAndAdmin_200_OK() {
		given().header("Authorization", userBasicAuth).then()
			.statusCode(SC_OK)
		.when().get("/spring-poc/lib/books");

		given().header("Authorization", adminBasicAuth).then()
			.statusCode(SC_OK)
		.when().get("/spring-poc/lib/books");
	}
	
	@Test
	public void geSpecifictBook_401() {
		given().header("Authorization", incorrectBasicAuth)
			.param("bookId", "12345").then()
		.statusCode(SC_UNAUTHORIZED)
			.when().get("/spring-poc/lib");
	}
	
	@Test
	public void geSpecifictBookAsUserAndAdmin_200_OK() {
		given().header("Authorization", userBasicAuth)
			.param("bookId", "12345").then()
		.statusCode(SC_OK)
			.when().get("/spring-poc/lib");

		given().header("Authorization", adminBasicAuth)
			.param("bookId", "12345").then()
		.statusCode(SC_OK).
			when().get("/spring-poc/lib");
	}
	
	// @formatter:on
}
