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
	/**
	public static void main(String[] args) {
		AccountInformationService ais = new AccountInformationService();
		List<Account> l1 = ais.getAllAccounts();
		List<Account> l2 = ais.accountsByUser(1);
		List<Account> l3 = ais.accountsByStatus(2);
		Account account = ais.getAccount(7);
		System.out.println("get all");
		for(int i = 0 ; i<l1.size() ; ++i) {
			System.out.println(l1.get(i).getBalance());
		}
		System.out.println("get by user");
		for(int i = 0 ; i<l2.size() ; ++i) {
			System.out.println(l2.get(i).getBalance());
		}
		System.out.println("get by status");
		for(int i = 0 ; i<l3.size() ; ++i) {
			System.out.println(l3.get(i).getBalance());
		}
		System.out.println("get one by id");
		System.out.println(account.getBalance());
	}
	**/
}
