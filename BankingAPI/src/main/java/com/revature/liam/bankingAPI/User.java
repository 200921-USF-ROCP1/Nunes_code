package com.revature.liam.bankingAPI;

public class User {
	private int userID;//get
	private String userName;//get and set
	private String password;//get and set for now, change when encrypting
	private String firstName;//get and set
	private String lastName;//get and set
	private String email;//get and set
	private Role myRole;//get and change
	private Account[] accounts = new Account[1];//add, remove by account id, get by account id	| change to array list of strings when database set up and manipulate accounts through driver with account# sore userID on account and accountID on user as froign keys
	
	//constructor for the user class inputs everything needed for user account
	public User(int userID , String password , String firstName , String lastName , String email , int roleId) {
		this.userID = userID;//generate based off of database in future
		this.password = password;//create a more secure hashing and salting for this
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		myRole = new Role(roleId);
	}
	
	//get user returns a string [userID, userName, lastName, firstName, role, email, account1#, account2#, ext...] will need string builder
	String getUser() {
		return "";
	}
	
	//password getter and setter (get rid of getter when i figure out hashing)
	String getPassword() {
		return password;
	}
	void setPassword(String password) {
		this.password = password;
	}
	
	//get userID (no setter)
	int getUserID() {
		return userID;
	}
	
	//get and set username firstname lastname email
	String getUSerName() {
		return userName;
	}
	void setUserName(String newUserName) {
		userName = newUserName;
	}
	String getFirstName() {
		return firstName;
	}
	void setFirstName(String newName) {
		firstName = newName;
	}
	String getLastName() {
		return lastName;
	}
	void setLastName(String newName) {
		firstName = newName;
	}
	String getEmail() {
		return email;
	}
	void setEmail(String email) {
		this.email = email;
	}
	
	//get and change role
	Role getRole() {
		return myRole;
	}
	void ChangeRole(int roleID) {
		myRole.switchRole(roleID);
	}
	
	//account getter and adder// change or move to driver when database phase starts
	void addAccount(int accountID, float startingFunds, int accountStatusId, int accountTypeID) {//change late
		for (int i = 0; i < accounts.length ; ++i) {
			if(accounts[i] == null) {
				accounts[i] = new Account(accountID,startingFunds,accountStatusId,accountTypeID);
				if (i == (accounts.length - 1)){
					doubleAccountArray();
				}
				i = accounts.length+1;
			}
		}
	}
	void doubleAccountArray() { // change later
		Account[] newAccounts = new Account[accounts.length];
		for (int i = 0; i < accounts.length ; ++i) {
			newAccounts[i] = accounts[i];
		}
		accounts = newAccounts;
	}
	Account getAccount(int accountID) { // get rid of later
		for (int i = 0; i < accounts.length ; ++i) {
			if(accounts[i].getAccountId() == accountID) {
				return accounts[i];
			}
		}
		return null;
	}
	
}
