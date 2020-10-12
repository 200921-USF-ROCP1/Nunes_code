package com.revature.liam.services;

import com.revature.daos.*;
import com.revature.models.Account;
import com.revature.models.User;

public class AccountEditingService {
	public void submitAccount(float startingFunds, int statusID, int TypeID, int userID) {
		Account account = new Account();
		account.setBalance(startingFunds);
		AccountTypeDAO atd = new AccountTypeDAO();
		account.setAccountType(atd.getAccountType(TypeID));
		AccountStatusDao asd = new AccountStatusDao();
		account.setAccountStatus(asd.getStatus(statusID));
		AccountDAO ad = new AccountDAO();
		int temp = ad.createAccount(account);
		UserAccountsDao uad = new UserAccountsDao();
		UserDAO ud = new UserDAO();
		User user = ud.getUserByID(userID);
		account.setAccountID(temp);
		uad.createUserAccount(user, account);
	}
	public void updateAccount(int id, float startingFunds, int statusID, int TypeID) {
		Account account = new Account();
		account.setAccountID(id);
		account.setBalance(startingFunds);
		AccountTypeDAO atd = new AccountTypeDAO();
		account.setAccountType(atd.getAccountType(TypeID));
		AccountStatusDao asd = new AccountStatusDao();
		account.setAccountStatus(asd.getStatus(statusID));
		AccountDAO ad = new AccountDAO();
		ad.updateAccount(account);
		
	}
	
	public static void main(String[] args) {
		AccountEditingService aes = new AccountEditingService();
		aes.updateAccount(1,(float) 11000.03, 2, 1);
	}
}
