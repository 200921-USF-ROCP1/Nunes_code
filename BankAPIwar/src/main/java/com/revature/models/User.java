package com.revature.models;


import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class User {
	private int userID;//get
	private String userName;//get and set
	private String password;//get and set for now, change when encrypting
	private String firstName;//get and set
	private String lastName;//get and set
	private String email;//get and set
	private Role myRole;//get and change
	byte[] salt = new byte[16];
	
	
	public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	//first time making new password use this to hash it
	public void setNewPassword(String password) {
		this.password = hasher(password,salt);
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Role getMyRole() {
		return myRole;
	}


	public void setMyRole(Role myRole) {
		this.myRole = myRole;
	}


	public String getSalt() {
		String bs = Base64.getEncoder().encodeToString(salt);
		return bs;
	}


	public void setSalt(String mySalt) {
		byte[] salt = Base64.getDecoder().decode(mySalt);
		this.salt = salt;
	}
	
	public void newSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		this.salt = salt;
	}
	
	//hash method
	public String hasher(String password,byte[] salt) {
		try {
			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = factory.generateSecret(spec).getEncoded();
			String temp = Base64.getEncoder().encodeToString(hash);
			return temp;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//check password
	public boolean checkPassowrd(String password) {
		String inputPassword = hasher(password,salt);
		if(password.equals(inputPassword)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static String marshalToJson(User user) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonString = mapper.writeValueAsString(user);
			return jsonString;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static User unmarshalFromJson(String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			User unmarshalled = mapper.readValue(json, User.class);
			return unmarshalled;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	public static void main (String... args) {
		User testUser = new User();
		testUser.newSalt();
		testUser.setNewPassword("Password");
		String password = testUser.getPassword();
		System.out.println(password);
		System.out.println(testUser.getSalt());
		
		testUser.setSalt(testUser.getSalt());
		System.out.println(testUser.getSalt());
		
		testUser.setNewPassword("Password");
		if(testUser.getPassword().equals(password)) {System.out.println("True");}
		
	}
	**/
	
}
