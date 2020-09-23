package com.pillarsOfOOP;

public class Encapsulation {
	//declare data
	private int i, j ,k;
	private String name;
	
	//getters
	public int getI() {
		return i;
	}
	public String getName() {
		return name;
	}
	
	//setters
	public void setName(String name) {
		this.name = name;
	}
	protected void setI(int i) {
		this.i = i;
	}
}
