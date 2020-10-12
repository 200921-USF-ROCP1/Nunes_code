package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.liam.interfaces.UserDaoInterface;
import com.revature.liam.services.ConnectionService;
import com.revature.models.Role;
import com.revature.models.User;

public class UserDAO implements UserDaoInterface{
	public int createUser(User user) {
		int idNum = 0;
		try {
			//open connection
			Connection connection = ConnectionService.getConnection();
			//create new user row
			PreparedStatement ps = connection.prepareStatement("INSERT INTO users (userName, password, salt, firstname, lastname, email, roleid) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getSalt());
			ps.setString(4, user.getFirstName());
			ps.setString(5, user.getLastName());
			ps.setString(6, user.getEmail());
			ps.setInt(7, user.getMyRole().getRoleID());
			ps.executeUpdate();
			//get id of new user
			PreparedStatement ps2 = connection.prepareStatement("SELECT id FROM users WHERE username = ?");
			ps2.setString(1,user.getUserName());
			ResultSet rs = ps2.executeQuery();
			if(rs.next()) {
				idNum = rs.getInt("id");
			}
			//closeConnection
			//ConnectionService.closeConnection();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//closeConnection
			//ConnectionService.closeConnection();
			e.printStackTrace();
		}
		return idNum;
	}

	public User getUserByID(int id) {
		try {
			//open connection
			Connection connection = ConnectionService.getConnection();
			//get user from id
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
			ps.setInt(1,id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				User user = new User();
				user.setUserID(rs.getInt("id"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setSalt(rs.getString("salt"));
				
				//set the role
				PreparedStatement roleStatement = connection.prepareStatement("SELECT * FROM roles WHERE id = ?");
				roleStatement.setInt(1, rs.getInt("roleid"));
				ResultSet roleRS = roleStatement.executeQuery();
				if(roleRS.next()) {
					Role role = new Role();
					role.setRoleID(roleRS.getInt("id"));
					role.setRoleName(roleRS.getString("rolename"));
					
					user.setMyRole(role);
				}
				//closeConnection
				//ConnectionService.closeConnection();
				
				return user;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//closeConnection
			//ConnectionService.closeConnection();
			e.printStackTrace();
		}
		return null;
	}

	public User getUserByUserName(String userName) {
		try {
			//open connection
			Connection connection = ConnectionService.getConnection();
			//get user from userName
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
			ps.setString(1,userName);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				User user = new User();
				user.setUserID(rs.getInt("id"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setSalt(rs.getString("salt"));
				//System.out.println(user.getUserID());
				
				//set the role
				PreparedStatement roleStatement = connection.prepareStatement("SELECT * FROM roles WHERE id = ?");
				int roleID = rs.getInt("roleid");
				roleStatement.setInt(1, roleID);
				//System.out.println(roleID);
				ResultSet roleRS = roleStatement.executeQuery();
				if(roleRS.next()) {
					Role role = new Role();
					role.setRoleID(roleRS.getInt("id"));
					role.setRoleName(roleRS.getString("rolename"));
					
					user.setMyRole(role);
				}
				//closeConnection
				//ConnectionService.closeConnection();
				
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//closeConnection
			//ConnectionService.closeConnection();
			e.printStackTrace();
		}
		return null;
	}

	public void updateUser(User user) {
		try {
			//open connection
			Connection connection = ConnectionService.getConnection();
			//create new user row
			PreparedStatement ps;
			ps = connection.prepareStatement("UPDATE users SET userName = ?, password = ?, salt = ?, firstname = ?, "
					+ "lastname = ?, email = ?, roleid = ? WHERE id = ? ");
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getSalt());
			ps.setString(4, user.getFirstName());
			ps.setString(5, user.getLastName());
			ps.setString(6, user.getEmail());
			ps.setInt(7, user.getMyRole().getRoleID());
			ps.setInt(8, user.getUserID());
			ps.executeUpdate();
			//close connection
			//ConnectionService.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//closeConnection
			//ConnectionService.closeConnection();
			e.printStackTrace();
		}
		
	}

	public void deleteUser(User user) {
		try {
			//open connection
			Connection connection = ConnectionService.getConnection();
			//delete user from row
			PreparedStatement ps = connection.prepareStatement("DELETE FROM users WHERE id = ?");
			ps.setInt(1, user.getUserID());
			ps.executeUpdate();
			//close connection
			//ConnectionService.closeConnection();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//closeConnection
			//ConnectionService.closeConnection();
			e.printStackTrace();
		}
	}

	public List<User> getAllUsers() {
		
		List<User> users = new ArrayList<User>();
		//open connection
		Connection connection = ConnectionService.getConnection();
		try {
			//get user from id
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM users");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setUserID(rs.getInt("id"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setSalt(rs.getString("salt"));
				
				//set the role
				PreparedStatement roleStatement = connection.prepareStatement("SELECT * FROM roles where id = ?");
				roleStatement.setInt(1, rs.getInt("roleid"));
				ResultSet roleRS = roleStatement.executeQuery();
				if(roleRS.next()) {
					Role role = new Role();
					role.setRoleID(roleRS.getInt("id"));
					role.setRoleName(roleRS.getString("rolename"));
					
					user.setMyRole(role);
					
					users.add(user);
				}
			}
				
			//closeConnection
			//ConnectionService.closeConnection();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			//closeConnection
			//ConnectionService.closeConnection();
			e.printStackTrace();
		}
		return users;
	}
}
