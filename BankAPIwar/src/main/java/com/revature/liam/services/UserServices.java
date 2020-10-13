package com.revature.liam.services;

import java.util.List;

import com.revature.daos.*;
import com.revature.models.*;

public class UserServices {
	public List<User> getAllUsers(){
		UserDAO ud = new UserDAO();
		return ud.getAllUsers();
	}
	public User getUserByID(int id) {
		UserDAO ud = new UserDAO();
		return ud.getUserByID(id);
	}
	public void updateUser(int id, String userName, String password, String firstName, String lastName, String email, int roleID) {
		User user = new User();
		user.setUserID(id);
		user.setUserName(userName);
		user.newSalt();
		user.setNewPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		RoleDao rd = new RoleDao();
		user.setMyRole(rd.getRole(roleID));
		UserDAO ud = new UserDAO();
		ud.updateUser(user);
	}
	
	/**
	public static void main(String[] args) {
		UserServices us = new UserServices();
		List<User> users= us.getAllUsers();
		for(int i = 0 ; i < users.size() ; ++i) {
			System.out.println(users.get(i).getFirstName());
		}
		User myUser = us.getUserByID(2);
		System.out.println(myUser.getFirstName());
		us.updateUser(2, "Jelly", "Peanuts" , "John" , "Adams", "j.adams@gmail.com", 3);
		for(int i = 0 ; i < users.size() ; ++i) {
			System.out.println(users.get(i).getFirstName());
		}
	}
	**/
}
