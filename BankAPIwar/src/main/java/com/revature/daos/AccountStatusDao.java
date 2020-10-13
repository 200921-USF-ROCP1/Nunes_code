package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.liam.services.ConnectionService;
import com.revature.models.*;

public class AccountStatusDao {
	public AccountStatus getStatus(int id) {
		try {
			//open connection
			Connection connection = ConnectionService.getConnection();
			//get role from id
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM accountsstatus WHERE id = ?");
			ps.setInt(1,id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				AccountStatus as = new AccountStatus();
				as.setStatusID(rs.getInt("id"));
				as.setStatus(rs.getString("status"));
				
				//closeConnection
				//ConnectionService.closeConnection();
				
				return as;
				
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
