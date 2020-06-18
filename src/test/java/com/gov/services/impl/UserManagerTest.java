package com.gov.services.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gov.GovProjectApplication;
import com.gov.services.UserManager;
import com.gov.services.entities.User;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = GovProjectApplication.class)
public class UserManagerTest {

	@Autowired
	private UserManager userManager;
	
	@Test
	public void testGetUsers() {
		// test there users returned
		User[] users = userManager.getUsers();
		assertNotNull(users);
	}
	
}
