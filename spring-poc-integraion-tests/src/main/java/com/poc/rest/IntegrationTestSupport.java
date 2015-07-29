package com.poc.rest;

import org.apache.http.HttpStatus;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;

public class IntegrationTestSupport implements HttpStatus {
	protected final static String userBasicAuth = "Basic dXNlcjpwYXNzd29yZA==";
	protected final static String adminBasicAuth = "Basic YWRtaW46YWRtaW4=";
	protected final static String incorrectBasicAuth = "Basic incorrectAuth";
	
	protected RequestSpecification given() {
		// @formatter:off
   	return RestAssured
   		.given()
   		.log()
   		.all();
   	// @formatter:on
	}
}