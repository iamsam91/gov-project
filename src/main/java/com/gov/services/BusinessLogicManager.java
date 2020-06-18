package com.gov.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.gov.services.entities.User;

@Service
public class BusinessLogicManager{

	Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	@Autowired
	UserManager userManager;
	
	@Value("${gov.milesToCheck}")
	private double milesDistanceToCheck;

	/**
	 * The latitudinal & longitudinal coordinates of Central London
	 */
	@Value("${gov.london.latlong}")
	private double[] londonLatLong;
	
	/**
	 * Filters a list of Users retrieved from an API to only Users that are within 50 miles of central London.
	 * This list is compiled by measuring the distance between the lat-long of the User and the lat-long of Central London
	 * 
	 * @return Array of Users that are from London or within 50 miles of central London
	 */
	public User[] getLondoners() throws RestClientException {
		
		User[] users = userManager.getUsers();
		
		logger.info("Retrieving users based in or within 50 miles of London");
		logger.info("London lat-long is [" + String.valueOf(londonLatLong[0]) + ", " + String.valueOf(londonLatLong[1]) + "]");
		
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
	
	/**
	 * @param latLongA Expects array of length 2 & latLongA[0] == latitude & latLongA[1] == longitude in degrees
	 * @param latLongB Expects array of length 2 & latLongB[0] == latitude & latLongB[1] == longitude in degrees
	 * @param distanceToCheck The max distance the difference between inputs latLongA & latLongB before returning a false
	 * @return  true = the difference is less than the distanceToCheck
	 * 			false = the difference is greater than the distanceToCheck
	 */
	private boolean isWithinDistance(double[] latLongA, double[] latLongB, double distanceToCheck) {
		double distance = haversineCalculation(latLongA, latLongB);
		if (distanceToCheck>distance) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * This method calculates the distance between to lat-long points in miles (statute) using the Haversine formula.
	 * The haversine formula determines the angular distance between two points on a sphere given their longitudes and latitudes
	 * 
	 * @param latLongA Expects array of length 2 & latLongA[0] == latitude & latLongA[1] == longitude in degrees
	 * @param latLongB Expects array of length 2 & latLongB[0] == latitude & latLongB[1] == longitude in degrees
	 * @return the length of the distance between the 2 points in miles
	 */
	private double haversineCalculation(double[] latLongA, double[] latLongB) {
		
		if (latLongA.length!=2 || latLongB.length!=2) {
			throw new NumberFormatException("latLong input array should have a length of 2."
					+ " The first number should be the latitude, the 2nd number should be the longitude.");
		}
		
		double lat1 = latLongA[0];
		double lat2 = latLongB[0];
		double lon1 = latLongA[1];
		double lon2 = latLongB[1];
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		}
		
		// https://en.wikipedia.org/wiki/Haversine_formula
		
		// a = sin(delta latitude /2)^2 + 
		//		cos(latitude1) * cos(latitude2) * sin(delta longitude /2)^2
		
		// c = 2*atan2( sqrt(a), sqrt(1-a) )
		
		// d = R * c
		
		double deltaLatitude = lat1 - lat2;
		double deltaLongitude = lon1 - lon2;
		double sin1 = (Math.sin(Math.toRadians(deltaLatitude)/2.0));
		double sin2 = Math.sin(Math.toRadians(deltaLongitude)/2.0);
		double a = (sin1 * sin1) +
				Math.cos(Math.toRadians(lat1)) * 
				Math.cos(Math.toRadians(lat2)) * 
				(sin2 * sin2);
		double c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double r = 6371.0;
		// in kilometres
		double d = r * c;
		
		// convert to miles
		double miles = d / 1.609344;

		return (miles);
	}
}
