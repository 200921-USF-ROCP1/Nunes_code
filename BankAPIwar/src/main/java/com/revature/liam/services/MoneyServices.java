package com.revature.liam.services;

import com.revature.daos.*;
import com.revature.models.*;

public class MoneyServices {
	public void withdraw(int accountID, float amount) {
		AccountDAO ad = new AccountDAO();
		Account account = ad.getAccountByID(accountID);
		account.setBalance(account.getBalance()-amount);
		ad.updateAccount(account);
	}
	public void deposit(int accountID, float amount) {
		AccountDAO ad = new AccountDAO();
		Account account = ad.getAccountByID(accountID);
		account.setBalance(account.getBalance()+amount);
		ad.updateAccount(account);
	}
	public void transfer(int accountIDFrom,int accountIDTo, float amount) {
		AccountDAO ad = new AccountDAO();
		Account accountFrom = ad.getAccountByID(accountIDFrom);
		Account accountTo = ad.getAccountByID(accountIDTo);
		accountFrom.setBalance(accountFrom.getBalance()-amount);
		accountTo.setBalance(accountTo.getBalance()+amount);
		ad.updateAccount(accountFrom);
		ad.updateAccount(accountTo);
	}
	/**
	public static void main(String... args) {
		MoneyServices ms = new MoneyServices();
		ms.deposit(7, 2000);
		ms.withdraw(8, 100);
		ms.transfer(5, 12, (float) 10000.5);
	}
	**/
	
}
