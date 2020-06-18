package com.gov.services.entities;

/**
 * @author Sam Payne
 *
 * Client User class to enable use of gov API
 */
public class User {

//  Example user from API docs
//	{
//	    "id": 1,
//	    "first_name": "Maurise",
//	    "last_name": "Shieldon",
//	    "email": "mshieldon0@squidoo.com",
//	    "ip_address": "192.57.232.111",
//	    "latitude": 34.003135,
//	    "longitude": -117.7228641
//	  }
	
	private int id;
	private String first_name;
	private String last_name;
	private String email;
	private String ip_address;
	private double latitude;
	private double longitude;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	
	
}
