package com.poc.config.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.poc.aop.InfoLogger;

@Configuration
@EnableAspectJAutoProxy
public class InfoLoggerConfig {
	@Bean
	public InfoLogger infoLogger() {
		return new InfoLogger();
	}
}
