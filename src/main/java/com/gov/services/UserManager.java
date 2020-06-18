package com.gov.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.gov.services.entities.User;


@Service
public class UserManager {

	Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	RestTemplate restTemplate = new RestTemplateBuilder().build();
	@Value("${gov.api}")
	private String url;
	
	/**
	 * Consumes a REST Api - URL set within the application.properties file by property 'gov.api'. 
	 * Expects a REST interface class to be in format of com.gov.services.entities.User
	 * 
	 * @return an Array of Users
	 */
	public User[] getUsers() throws RestClientException {
		String fullUrl = this.url + "users";
		logger.info("URL for REST Api: " +  fullUrl);
		User[] users = restTemplate.getForObject(
				fullUrl, User[].class);
		return users;
	}

	
}
