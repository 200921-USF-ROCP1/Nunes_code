package com.revature.liam;

import java.io.FileReader;

public class Liam {
	//Data types : all types are 2^n
	
	//primitive types or value types
	boolean bool; // 1 bit true/false
	byte b; // 8 bits
	char c; // 16 bits represents a 16 bit table 
	short s; // 16 bits
	int i; // 32 bits
	float f; // 32 bits (standard decimal not to big)
	long l; // 64 bits
	double d; // 64 bits (need a bigger or more precise decimal
	
	//reference types or address of something in the heap
	String str; // glorified char array
	Object o;
	FileReader r;
	//ect...
	
	//no access modifier = default access modifier
	//anything in the package can acess
	//also known as package private
	void myDefaultMethod() {}
	
	//protected means it can be accessed form inside the class
	//and any subclasses (aka child classes)
	protected void myProtectedMethod() {}
	
	//private mothods and variavbles cant be seen from anywhere but in the class
	private void myPrivateMethod() {}
	
	//classes can only be public or default
	
	
	//contructors
	//no param construtor
	public Liam() {
		System.out.println("inside liam contstructor");
	}
	//paramiter construtor
	public Liam(int i) {
		this.i = i;
	}
	
	//java favors the most specific scope
	//class scope
	//method score
	
	//i here overshadows teh class' i
	public void scopes (int i) {
		//use this to get to class variables in theat case
		this.i = i;
	}
	
	//control statements
	public void control(boolean yes, int vall) {
		if (yes) {
			//do somthing if yes is true
		} else  if(vall ==2){
			//do somthing if val is 2
		}
		else {
			
		}
		
		//for loops iterate a certain number of times (declaration; condition; what to do after each iteration)
		for(int i = 0; i<vall; ++i) {
			if(i=0) {
				continue;
				//continue skips 1 itteration of the loop
			}
			break;
		}
		
		while(yes) {
			//do somthing while yes == true
			break;
		}
		do {
			//do once then run as long as yes is true
			break;
		}while(yes);
		
		switch (vall) {
		case 0:
			//do somthing if val is 0
			break;
		case 1:
			//do somthign if vall == 1
			break;
		default:
			//if no other cases match do this
			break;
		
		}
	}
}
