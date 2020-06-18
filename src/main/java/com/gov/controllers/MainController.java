package com.gov.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.gov.services.BusinessLogicManager;
import com.gov.services.entities.User;


/**
 * Main Controller class for API. 
 * 
 * @author Sam Payne
 */
@RestController
@RequestMapping("/api")
public class MainController {

	Logger logger = LoggerFactory.getLogger(getClass().getPackage().getName());
	@Autowired
	BusinessLogicManager mainManager;
	
	/**
	 * @return people who are listed as either living in London, or whose current coordinates are within 50 miles of London.
	 */
	@GetMapping(value = "/users/london")
	public ResponseEntity<User[]> londonUsers() {
		try {
			User[] users = mainManager.getLondoners();
			logger.info("Succesfully retrieved users!");
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (RestClientException ex) {
			logger.error("Cannot retrieve users", ex);
			return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
		} catch (Exception ex) {
			logger.error("Cannot retrieve users", ex);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
