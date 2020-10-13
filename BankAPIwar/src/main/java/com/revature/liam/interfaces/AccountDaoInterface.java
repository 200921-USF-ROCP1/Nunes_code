package com.revature.liam.interfaces;

import java.util.List;

import com.revature.models.Account;

public interface AccountDaoInterface {
	
	public int createAccount(Account account);
	
	public Account getAccountByID(int id);
	
	public List<Account> getAccountsByUserID(int id);
	
	public List<Account> getAccountsBySatus(int id);
	
	public List<Account> getAllAccounts();
	
	public void updateAccount(Account account);
	
	public void deleteAccount(Account account);
	
}
