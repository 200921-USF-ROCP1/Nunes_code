package com.revature.liam.services;

import java.util.List;

import com.revature.daos.AccountDAO;
import com.revature.models.Account;

public class AccountInformationService {
	public List<Account> getAllAccounts(){
		AccountDAO ad = new AccountDAO();
		return ad.getAllAccounts();
	}
	public List<Account> accountsByUser(int userID){
		AccountDAO ad = new AccountDAO();
		return ad.getAccountsByUserID(userID);
	} 
	public List<Account> accountsByStatus(int statusID){
		AccountDAO ad = new AccountDAO();
		return ad.getAccountsBySatus(statusID);
	} 
	public Account getAccount(int id) {
		AccountDAO ad = new AccountDAO();
		return ad.getAccountByID(id);
	}
}
