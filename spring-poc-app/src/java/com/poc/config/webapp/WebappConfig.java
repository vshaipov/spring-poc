package com.poc.config.webapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebMvc
@ComponentScan({ "com.poc" })
public class WebappConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ObjectMapper objectMapper(){
	return new ObjectMapper();
    } 
    
    protected void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests().anyRequest().authenticated().and().formLogin()
		.and().httpBasic();
    }
}
