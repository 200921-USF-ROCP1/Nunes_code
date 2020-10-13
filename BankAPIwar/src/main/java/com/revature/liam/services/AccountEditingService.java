package com.revature.liam.services;

import com.revature.daos.*;
import com.revature.models.Account;
import com.revature.models.User;

public class AccountEditingService {
	public void submitAccount(float startingFunds, int statusID, int TypeID, int userID) {
		//daos
		AccountTypeDAO atd = new AccountTypeDAO();
		AccountStatusDao asd = new AccountStatusDao();
		AccountDAO ad = new AccountDAO();
		UserAccountsDao uad = new UserAccountsDao();
		UserDAO ud = new UserDAO();
		//objects
		Account account = new Account();
		User user = ud.getUserByID(userID);
		
		account.setBalance(startingFunds);
		account.setAccountType(atd.getAccountType(TypeID));
		account.setAccountStatus(asd.getStatus(statusID));
		int temp = ad.createAccount(account);
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
	/**
	public static void main(String[] args) {
		AccountEditingService aes = new AccountEditingService();
		aes.submitAccount((float) 110000.03, 2, 1,1);
		aes.submitAccount((float) 190.03, 2, 2,2);
		aes.submitAccount((float) 80000.96, 2, 1,3);
		aes.submitAccount((float) 2000.07, 2, 1,4);
		aes.submitAccount((float) 86.81, 2, 1,5);
		aes.submitAccount((float) 0, 4, 1,6);
		aes.submitAccount((float) 90.03, 1, 1,7);
		aes.submitAccount((float) 500.84, 2, 2,1);
		aes.submitAccount((float) 0, 3, 1,2);
	}
	**/
}
