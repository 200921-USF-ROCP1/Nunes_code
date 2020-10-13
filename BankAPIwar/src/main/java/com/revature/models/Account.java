package com.revature.models;

public class Account {
	private int accountID;
	private float balance = 0;//needs to be formatted
	private AccountStatus accountStatus;
	private AccountType accountType;
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public AccountStatus getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
	
	//add interest method store days and interest so i can calculate interest daily then add at the end of the month (base on calender days? ) add to interest and day counter if its a savings
	
}
