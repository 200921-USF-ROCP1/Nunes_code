package com.revature.liam.services;

import com.revature.models.*;

import java.util.Base64;

import com.revature.daos.*;

public class SecurityService {
	public User login(String userName , String password) {
		UserDAO ud = new UserDAO();
		User user = ud.getUserByUserName(userName);
		if(user == null) {
			return null;
		}
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
	public User register(String userName, String password, String firstName, String lastName, String email, int roleID) {
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
		User temp = ud.getUserByUserName(userName);
		if (temp == null) {
			user.setUserID(ud.createUser(user));
			return user;
		}
		else {return null;}
	}
	/**
	public static void main(String... args) {
		ConnectionService.closeConnection();
		SecurityService ss = new SecurityService();
		User i =ss.register("WhoDis", "Apples", "Alice", "Reynolds", "ar15@jokemail.com", 2);
		i = ss.register("DCabs", "Speed", "Dave", "Cabral", "DrDave@gmail.com", 2);
		i = ss.register("JohnnyMass", "Lucky", "John", "Erickson", "BigMoney@hotmail.com", 2);
		i = ss.register("Gibbers", "Tia", "Corey", "Gibb", "CGibb@gmail.com", 2);
		i = ss.register("TiaTots", "Princess01", "Tia", "Millete", "TiaMdog@aol.com", 2);
		i = ss.register("TheBeast", "Mothman", "Anna", "Lobe", "Alobes@anon.com", 2);
		i = ss.register("JoshB", "PartyTime", "Josh", "Brown", "jb63@jokemail.com", 2);
		ConnectionService.closeConnection();
		
	}
	**/
	
	
}
