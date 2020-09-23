package com.pillarsOfOOP;

public class Inheritence {
	private String name;
	private int age;
	
	public Inheritence (String name, int age) {
		this.name = name;
		this.age = age;
	}
	 public String getName() {
		 return name;
	 }
	 public int getAge() {
		 return age;
	 }
	 public void becomeOlder() {
		 ++age;
	 }
	 public void printSelf() {
		 System.out.println("I am parent");
	 }
	
	
}
