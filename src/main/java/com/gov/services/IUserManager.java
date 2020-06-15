package com.gov.services;

import org.springframework.stereotype.Service;

import com.gov.services.entities.User;

@Service
public interface IUserManager {

	public User[] getUsers();
	
}
