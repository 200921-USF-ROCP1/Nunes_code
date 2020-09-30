package com.revature.liam.bankingAPI;

public class AccountStatus {
	private int accountID;
	private String status;
	public AccountStatus(int accountID){
		//enter conditional here to check if id is valid and throw error if not
		this.accountID = accountID;
		setStatus();
	}
	//set type based on accountID
	private void setStatus() {
		//set switch to set type
		status = "";
	}
	
	//getters
	public int getStatusID(){
		return accountID;
	}
	public String getStatus() {
		return status;
	}
}
