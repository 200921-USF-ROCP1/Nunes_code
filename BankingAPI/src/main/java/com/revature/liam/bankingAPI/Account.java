package com.revature.liam.bankingAPI;

public class Account {
	private int accountID;
	private float balance = 0;//needs to be formatted
	private AccountStatus accountStatus;
	private AccountType accountType;
	
	public Account(int accountID, float startingFunds, int accountStatusId, int accountTypeID) {
		this.accountID = accountID;
		accountStatus = new AccountStatus(accountStatusId);
		accountType = new AccountType(accountTypeID);
		//formatting for balance and starting funds?
		addFunds(startingFunds);
	}
	
	//add and subtract funds
	void addFunds(float amount) {
		balance = balance + amount;
	}
	void subtractFunds(float amount) {
		balance = balance - amount;
	}
	
	//getters
	float getBalance() {
		return balance;
	}
	int getAccountId() {
		return accountID;
	}
	AccountStatus getAccountStatus() {
		return accountStatus;
	}
	AccountType getAccountType() {
		return accountType;
	}
	//add interest method store days and interest so i can calculate interest daily then add at the end of the month (base on calender days? ) add to interest and day counter if its a savings
	
}
