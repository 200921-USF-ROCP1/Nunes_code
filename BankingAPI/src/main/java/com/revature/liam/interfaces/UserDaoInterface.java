package com.revature.liam.interfaces;

import java.util.List;

import com.revature.models.User;


public interface UserDaoInterface {
	public int createUser(User user);
	
	public User getUserByID(int id);
	
	public User getUserByUserName(String userName);
	
	public void updateUser(User user);
	
	public void deleteUser(User user);
	
	public List<User> getAllUsers();
}
