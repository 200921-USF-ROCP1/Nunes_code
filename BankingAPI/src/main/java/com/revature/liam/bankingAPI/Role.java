package com.revature.liam.bankingAPI;

public class Role {
	private int roleID;
	private String roleName;
	public Role(int roleID){
		//enter conditional here to check if id is valid and throw error if not
		this.roleID = roleID;
		setRole();
	}
	//set type based on roleID
	private void setRole() {
		//set switch to set type
		roleName = "";
	}
	
	//getters
	public int getroleID(){
		return roleID;
	}
	public String getRoleName() {
		return roleName;
	}
	
	//change role
	public void switchRole(int roleID) {
		this.roleID = roleID;
		setRole();
	}
}
