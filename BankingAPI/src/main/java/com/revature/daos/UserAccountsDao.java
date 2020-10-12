package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.liam.services.ConnectionService;
import com.revature.models.Account;
import com.revature.models.User;

public class UserAccountsDao {
	public void createUserAccount(User user, Account account) {
		try {
			//open connection
			Connection connection = ConnectionService.getConnection();
			//create new account
			PreparedStatement ps = connection.prepareStatement("INSERT INTO usersaccounts (userid, accountid)"
					+ "VALUES (?, ?)");
			ps.setInt(1, user.getUserID());
			ps.setInt(2, account.getAccountID());
			ps.executeUpdate();
			
		}catch(SQLException e) {
			// TODO Auto-generated catch block
			//closeConnection
			//ConnectionService.closeConnection();
			e.printStackTrace();
		}
	}
}
