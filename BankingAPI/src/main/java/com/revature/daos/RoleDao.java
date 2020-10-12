package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.liam.services.ConnectionService;
import com.revature.models.Role;


public class RoleDao {
	public Role getRole(int id) {
		try {
			//open connection
			Connection connection = ConnectionService.getConnection();
			//get role from id
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM roles WHERE id = ?");
			ps.setInt(1,id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Role role = new Role();
				role.setRoleID(rs.getInt("id"));
				role.setRoleName(rs.getString("rolename"));
				
				//closeConnection
				//ConnectionService.closeConnection();
				
				return role;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//closeConnection
			ConnectionService.closeConnection();
			e.printStackTrace();
		}
		return null;
	}
}
