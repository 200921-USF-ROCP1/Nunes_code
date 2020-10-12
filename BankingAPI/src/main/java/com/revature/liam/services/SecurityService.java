package com.revature.liam.services;

import com.revature.models.*;

import java.util.Base64;

import com.revature.daos.*;

public class SecurityService {
	public User login(String userName , String password) {
		UserDAO ud = new UserDAO();
		User user = ud.getUserByUserName(userName);
		byte[] salt = Base64.getDecoder().decode(user.getSalt());
		String hashed = user.hasher(password, salt);
		if(user.getPassword().equals(hashed)) {
			//user.setSalt(null);
			user.setPassword(null);
			return user;
		}
		else {
			return null;
		}
	}
	//logout handled through servlet
	public void register(String userName, String password, String firstName, String lastName, String email, int roleID) {
		User user = new User();
		user.setUserName(userName);
		user.newSalt();
		user.setNewPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		RoleDao rd = new RoleDao();
		user.setMyRole(rd.getRole(roleID));
		UserDAO ud = new UserDAO();
		user.setUserID(ud.createUser(user));
	}
	/**
	public static void main(String... args) {
		ConnectionService.closeConnection();
		SecurityService ss = new SecurityService();
		ss.register("WhoDis", "Apples", "Alice", "Reynolds", "ar15@jokemail.com", 2);
		User test2 = ss.login("Random", "Peanuts");
		if(test2 != null) {
			System.out.println(test2.getFirstName());
		}
		ConnectionService.closeConnection();
		
	}
	**/
}
