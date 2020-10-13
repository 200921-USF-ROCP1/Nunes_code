package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.liam.interfaces.AccountDaoInterface;
import com.revature.liam.services.ConnectionService;
import com.revature.models.Account;
import com.revature.models.AccountStatus;
import com.revature.models.AccountType;

public class AccountDAO implements AccountDaoInterface{

	public int createAccount(Account account) {
		int idNum = 0;
		try {
			//open connection
			Connection connection = ConnectionService.getConnection();
			//create new account
			PreparedStatement ps = connection.prepareStatement("INSERT INTO accounts (balance, statusID, typeID)"
					+ "VALUES (?, ?, ?)");
			ps.setFloat(1, account.getBalance());
			ps.setInt(2, account.getAccountStatus().getStatusID());
			ps.setInt(3, account.getAccountType().getTypeID());
			
			ps.executeUpdate();
			//get id of new account
			PreparedStatement ps2 = connection.prepareStatement("SELECT id FROM accounts ORDER BY id DESC LIMIT 1");
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

	public Account getAccountByID(int id) {
		try {
			//open connection
			Connection connection = ConnectionService.getConnection();
			//get account from id
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM accounts WHERE id = ?");
			ps.setInt(1,id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Account account = new Account();
				account.setAccountID(rs.getInt("id"));
				account.setBalance(rs.getFloat("balance"));
				
				//set the status
				PreparedStatement accountStatement = connection.prepareStatement("SELECT * FROM accountsstatus WHERE id = ?");
				accountStatement.setInt(1, rs.getInt("statusID"));
				ResultSet statusRS = accountStatement.executeQuery();
				AccountStatus accountStatus = new AccountStatus();
				
				if(statusRS.next()) {
					accountStatus.setStatusID(statusRS.getInt("id"));
					accountStatus.setStatus(statusRS.getString("status"));
					account.setAccountStatus(accountStatus);
				}
				
				//set the type
				PreparedStatement typeStatement = connection.prepareStatement("SELECT * FROM accountstypes WHERE id = ?");
				typeStatement.setInt(1, rs.getInt("typeID"));
				ResultSet typeRS = typeStatement.executeQuery();
				AccountType accounttype = new AccountType();
				
				if(typeRS.next()) {
					accounttype.setTypeID(typeRS.getInt("id"));
					accounttype.setType(typeRS.getString("accounttype"));
					account.setAccountType(accounttype);
				}
				
				//closeConnection
				//ConnectionService.closeConnection();
				
				return account;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//closeConnection
			//ConnectionService.closeConnection();
			e.printStackTrace();
			
		}
		return null;
	}

	public List<Account> getAccountsByUserID(int id) {
		//open connection
		Connection connection = ConnectionService.getConnection();
		List<Account> accounts = new ArrayList<Account>();
		//get list of accountIDs where there is a matching user id
		List<Integer> accountIDs = new ArrayList<Integer>();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM usersaccounts WHERE userid = ?");
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				accountIDs.add(rs.getInt("accountid"));
			}
			for(int i = 0 ; i < accountIDs.size() ; ++i) {
				accounts.add(getAccountByID(accountIDs.get(i)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//closeConnection
			//ConnectionService.closeConnection();
			e.printStackTrace();
		}
		//closeConnection
		//ConnectionService.closeConnection();
		return accounts;
	}

	public List<Account> getAccountsBySatus(int id) {
		List<Account> accounts = new ArrayList<Account>();
		try {
			//open connection
			Connection connection = ConnectionService.getConnection();
			//get account from id where ststus id = input
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM accounts WHERE statusID = ?");
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Account account = new Account();
				account.setAccountID(rs.getInt("id"));
				account.setBalance(rs.getFloat("balance"));
				
				//set the status
				PreparedStatement accountStatement = connection.prepareStatement("SELECT * FROM accountsstatus WHERE id = ?");
				accountStatement.setInt(1, rs.getInt("statusID"));
				ResultSet statusRS = accountStatement.executeQuery();
				AccountStatus accountStatus = new AccountStatus();
				
				if(statusRS.next()) {
					accountStatus.setStatusID(statusRS.getInt("id"));
					accountStatus.setStatus(statusRS.getString("status"));
					account.setAccountStatus(accountStatus);
				}
				
				//set the type
				PreparedStatement typeStatement = connection.prepareStatement("SELECT * FROM accountstypes WHERE id = ?");
				typeStatement.setInt(1, rs.getInt("typeID"));
				ResultSet typeRS = typeStatement.executeQuery();
				AccountType accounttype = new AccountType();
				
				if(typeRS.next()) {
					accounttype.setTypeID(typeRS.getInt("id"));
					accounttype.setType(typeRS.getString("accounttype"));
					account.setAccountType(accounttype);
				}
				
				//closeConnection
				//ConnectionService.closeConnection();
				
				accounts.add(account) ;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//closeConnection
			//ConnectionService.closeConnection();
			e.printStackTrace();
		}
		return accounts;
	}

	public List<Account> getAllAccounts() {
		List<Account> accounts = new ArrayList<Account>();
		try {
			//open connection
			Connection connection = ConnectionService.getConnection();
			//get account from id
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM accounts");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Account account = new Account();
				account.setAccountID(rs.getInt("id"));
				account.setBalance(rs.getFloat("balance"));
				
				//set the status
				PreparedStatement accountStatement = connection.prepareStatement("SELECT * FROM accountsstatus WHERE id = ?");
				accountStatement.setInt(1, rs.getInt("statusID"));
				ResultSet statusRS = accountStatement.executeQuery();
				AccountStatus accountStatus = new AccountStatus();
				
				if(statusRS.next()) {
					accountStatus.setStatusID(statusRS.getInt("id"));
					accountStatus.setStatus(statusRS.getString("status"));
					account.setAccountStatus(accountStatus);
				}
				
				//set the type
				PreparedStatement typeStatement = connection.prepareStatement("SELECT * FROM accountstypes WHERE id = ?");
				typeStatement.setInt(1, rs.getInt("typeID"));
				ResultSet typeRS = typeStatement.executeQuery();
				AccountType accounttype = new AccountType();
				
				if(typeRS.next()) {
					accounttype.setTypeID(typeRS.getInt("id"));
					accounttype.setType(typeRS.getString("accounttype"));
					account.setAccountType(accounttype);
				}
				
				//closeConnection
				//ConnectionService.closeConnection();
				
				accounts.add(account) ;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//closeConnection
			//ConnectionService.closeConnection();
			e.printStackTrace();
		}
		return accounts;
	}

	public void updateAccount(Account account) {
		try {
			//open connection
			Connection connection = ConnectionService.getConnection();
			//update the account given on the 
			PreparedStatement ps;
			ps = connection.prepareStatement("UPDATE accounts SET balance = ?, statusID = ?, typeID = ? WHERE id = ?");
			ps.setFloat(1, account.getBalance());
			ps.setInt(2, account.getAccountStatus().getStatusID());
			ps.setInt(3, account.getAccountType().getTypeID());
			ps.setInt(4, account.getAccountID());
			
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

	public void deleteAccount(Account account) {
		try {
			//open connection
			Connection connection = ConnectionService.getConnection();
			//delete user from row
			PreparedStatement ps = connection.prepareStatement("DELETE FROM accunts WHERE id = ?");
			ps.setInt(1, account.getAccountID());
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

}
