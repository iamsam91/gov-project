package com.gov.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.gov.services.IBusinessLogicManager;
import com.gov.services.IUserManager;
import com.gov.services.entities.User;

@Service
public class BusinessLogicImpl implements IBusinessLogicManager{

	@Autowired
	IUserManager userManager;
	
	private final double[] londonLatLong = {51.507351, -0.127758};
	private final double milesDistanceToCheck = 50.0;

	@Override
	public User[] getLondoners() throws RestClientException {
		
		User[] users = userManager.getUsers();
		
		User[] londonUsers = new User[users.length];
		int index = 0;
		for (User user : users) {
			double[] userLatLong = {user.getLatitude(), user.getLongitude()};
			if (isWithinDistance(londonLatLong, userLatLong, milesDistanceToCheck)) {
				londonUsers[index++] = user;
			}
		}
		User[] trimmedArray = new User[index];
		int i = 0;
		while(i < index) {
			trimmedArray[i] = londonUsers[i++];
		}
		return trimmedArray;
	}
	
	private boolean isWithinDistance(double[] latLongA, double[] latLongB, double distanceToCheck) {
		
		double distance = haversineCalculation(latLongA, latLongB);
		if (distanceToCheck>distance) {
			return true;
		}
		return false;
	}
	
	private double haversineCalculation(double[] latLongA, double[] latLongB) {
		
		double lat1 = latLongA[0];
		double lat2 = latLongB[0];
		double lon1 = latLongA[1];
		double lon2 = latLongB[1];
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		}
		
		// https://en.wikipedia.org/wiki/Haversine_formula
		
		double theta = lon1 - lon2;
		double dist = Math.sin(Math.toRadians(lat1)) * 
				Math.sin(Math.toRadians(lat2)) + 
				Math.cos(Math.toRadians(lat1)) * 
				Math.cos(Math.toRadians(lat2)) * 
				Math.cos(Math.toRadians(theta));
		dist = Math.acos(dist);
		dist = Math.toDegrees(dist);
		dist = dist * 60 * 1.1515;

		return (dist);
	}
}
