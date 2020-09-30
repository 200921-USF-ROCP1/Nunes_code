package com.revature.liam.bankingAPI;

public class AccountType {
	private int typeID;
	private String type;
	public AccountType(int typeID){
		//enter conditional here to check if id is valid and throw error if not
		this.typeID = typeID;
		setType();
	}
	//set type based on typeID
	private void setType() {
		//set switch to set type
		type = "";
	}
	
	//getters
	public int getAccountTypeID(){
		return typeID;
	}
	public String getAccountType() {
		return type;
	}
	
	
}
