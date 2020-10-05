package com.revature.liam.bankingAPI;


import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class User {
	private int userID;//get
	private String userName;//get and set
	private String password;//get and set for now, change when encrypting
	private String firstName;//get and set
	private String lastName;//get and set
	private String email;//get and set
	private Role myRole;//get and change
	private Account[] accounts = new Account[1];//add, remove by account id, get by account id	| change to array list of strings when database set up and manipulate accounts through driver with account# sore userID on account and accountID on user as froign keys
	byte[] salt = new byte[16];
	
	//constructor for the user class to make new user entry inputs everything needed for new user account
	public User(int userID ,String userName, String password , String firstName , String lastName , String email , int roleId) {
		this.userID = userID;//generate based off of database in future
		//get random salt first time I create something;
		SecureRandom random = new SecureRandom();
		random.nextBytes(salt);
		this.password = hasher(password,salt);//create a more secure hashing and salting for this
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userName = userName;
		myRole = new Role(roleId);
		//add to database
	}
	//constructor for the user class to make new user entry inputs everything needed for new user account
	public User(String userName , String password) {
		//check database for user then build user object based off of that data else throw exception
		//set salt then check password else throw exception
	}
	
	//hash method
	private String hasher(String password,byte[] salt) {
		try {
			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = factory.generateSecret(spec).getEncoded();
			String temp = Base64.getEncoder().encodeToString(hash);
			return temp;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public String testHasher(String password) {
		return this.hasher(password, salt);
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
	
	/**
	public static void main (String... args) {
		User testUser = new User(1,"LiamNunes","password","LiamNunes","Nunes","Liam",1);
		System.out.println(testUser.password);
		System.out.println(testUser.testHasher("password"));
		if(testUser.password.equals(testUser.testHasher("password"))) {
			System.out.println("it Works");
		}
		User testUser2 = new User(1,"LiamNunes","password","LiamNunes","Nunes","Liam",1);
		System.out.println(testUser2.password);
		if(testUser.password.equals(testUser2.password)) {
			System.out.println("it doesn't Works");
		}
		byte[] decode = Base64.getDecoder().decode(testUser.password);
		for (int i = 0 ; i < decode.length ; ++i) {
			System.out.print(decode[i]);
		}
		
	}
	**/
}
