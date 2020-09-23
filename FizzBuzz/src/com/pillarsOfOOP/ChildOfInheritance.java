package com.pillarsOfOOP;

public class ChildOfInheritance extends Inheritence{
	public ChildOfInheritance(String name) {
		super(name,1);
		this.becomeOlder();
	}
	public ChildOfInheritance(String name, int age) {
		super(name,age);
		this.becomeOlder();
	}
	
	//print self overriding
	public void printSelf() {
		System.out.println("I am child");
	}
	
}
	
