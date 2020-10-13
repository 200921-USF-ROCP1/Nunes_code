package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.liam.services.ConnectionService;
import com.revature.models.*;

public class AccountTypeDAO {
	public AccountType getAccountType(int id) {
		try {
			//open connection
			Connection connection = ConnectionService.getConnection();
			//get from id
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM accountstypes WHERE id = ?");
			ps.setInt(1,id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				AccountType accountType = new AccountType();
				accountType.setTypeID(rs.getInt("id"));
				accountType.setType(rs.getString("accounttype"));
				
				//closeConnection
				//ConnectionService.closeConnection();
				
				return accountType;
				
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
