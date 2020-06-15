package com.gov.services.impl;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.gov.services.IUserManager;
import com.gov.services.entities.User;

@Service
public class UserManagerImpl implements IUserManager{

	RestTemplate restTemplate = new RestTemplateBuilder().build();
	private String url = "https://bpdts-test-app.herokuapp.com/";
	
	@Override
	public User[] getUsers() throws RestClientException {
		User[] users = restTemplate.getForObject(
				url + "users", User[].class);
		return users;
	}
	
}
