package com.poc.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class InfoLogger {
	private static Logger log = Logger.getLogger(InfoLogger.class);

	@Pointcut("execution(* *.geSpecifictBook(..)) && within(com.poc.controllers..*)")
	public void logGeSpecifictBook() {
	}
	
	@Pointcut("execution(* *.getBooks(..)) && within(com.poc.controllers..*)")
	public void logGetBooks() {
	}

	@Before("logGeSpecifictBook()")
	public void logBeforeGetSpecifictBook() {
		log.info("Starting calling getSpecifictBook");
	}

	@After("logGeSpecifictBook()")
	public void logAfterGetSpecifictBook() {
		log.info("Ending calling getSpecifictBook");
	}
	
	@Before("logGetBooks()")
	public void logBeforeGetBook() {
		log.info("Starting calling getBooks");
	}

	@After("logGetBooks()")
	public void logAfterGetBook() {
		log.info("Ending calling getBooks");
	}
}
