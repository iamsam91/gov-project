package com.gov.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;

import com.gov.GovProjectApplication;
import com.gov.services.BusinessLogicManager;
import com.gov.services.UserManager;
import com.gov.services.entities.User;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = GovProjectApplication.class)
public class BusinessLogicManagerTest {

	@Autowired
	private BusinessLogicManager blManager;
	@Autowired
	private UserManager userManager;
	
	public User[] fakeUsers() {
		User paul = new User();
		User john = new User();
		User ringo = new User();
		User george = new User();
		
		// in central london
		paul.setEmail("paul@beatles.co.uk");
		paul.setFirst_name("Paul");
		paul.setLast_name("McCartney");
		paul.setIp_address("192.57.232.111");
		paul.setLatitude(51.507351);
		paul.setLongitude(-0.127758);
		paul.setId(1);
		
		// in liverpool
		john.setEmail("john@beatles.co.uk");
		john.setFirst_name("John");
		john.setLast_name("Lennon");
		john.setIp_address("192.57.232.112");
		john.setLatitude(53.408371);
		john.setLongitude(-2.991573);
		john.setId(2);
		
		// 51 miles from london (whitstable)
		ringo.setEmail("ringo@beatles.co.uk");
		ringo.setFirst_name("Richard");
		ringo.setLast_name("Starkey");
		ringo.setIp_address("192.57.232.113");
		ringo.setLatitude(51.357079);
		ringo.setLongitude(1.024650);
		ringo.setId(2);
		
		// 49 miles away from london (cambridge)
		george.setEmail("george@beatles.co.uk");
		george.setFirst_name("George");
		george.setLast_name("Harrison");
		george.setIp_address("192.57.232.114");
		george.setLatitude(52.205338);
		george.setLongitude(0.121817);
		george.setId(2);
		
		User[] users = {paul, john, ringo, george};
		return users;
	}
	
	@BeforeEach
	public void reset() {
		Mockito.reset(userManager);
	}	
	
	@Test
	public void testGetLondoners() {
		
		Mockito.when(userManager.getUsers()).thenReturn(fakeUsers());
		
		User[] londonUsers = blManager.getLondoners();
		
		assertEquals(2, londonUsers.length);
		String user1 = londonUsers[0].getFirst_name();
		String user2 = londonUsers[1].getFirst_name();
		if (user1=="Paul") {
			assertEquals("Paul", user1);
			assertEquals("George", user2);
		} else if (user1=="George") {
			assertEquals("Paul", user2);
			assertEquals("George", user1);
		}
		
	}
	
	@Test
	public void testNoOrBadConnection() {
		
		Mockito.when(userManager.getUsers()).thenThrow(RestClientException.class);
		try {
			blManager.getLondoners();
			// Should not get to this fail
			fail("Failed to throw server error passed from user manager");
		} catch (Exception ex) {
			// Exception correctly thrown
			System.out.print(ex.getMessage());
			assertTrue(true);
		}
		
	}

}
