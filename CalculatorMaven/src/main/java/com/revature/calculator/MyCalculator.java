package com.revature.calculator;


public class MyCalculator implements Calculator{
	//blank constructor
	public MyCalculator() {
	}
	
	//a plus b
	public int add(int a, int b) {
		
		return a + b;
	}
	
	//a minus b
	public int subtract(int a, int b) {
		return a-b;
	}

	//a times b
	public int multiply(int a, int b) {
		return a*b;
	}

	//a divided by b
	public int divide(int a, int b) {
		return a/b;
	}

	//calculated x to the power of e
	public int exponent(int x, int e) {
		if (e == 0) {
			return 1;
		}
		return x * exponent(x,e-1);
	}

	//Implements fibonacci calc by adding a numner to the two that come before
	public int fibonacci(int i) {
		if (i<2) {
			return i;
		}
		else {
			return fibonacci(i-1)+fibonacci(i-2);
		}
	}
	
	//check to see if a string is an int return false if its not
	public boolean isInt(String s) {
		try {
			int z = Integer.parseInt(s);
			return true;
		}catch (NumberFormatException e){
			return false;
		}
	}

	//parses the given string and tries to read it as a simple mathmatical expression
	public int parse(String s) {
		
		//split the string into elements
		String[] elements = s.split(" ");
		
		int x = 0;
		int y = 0;
		int z = 0;
		
		//check for valid input
		if (elements.length < 2) {
			System.out.println("Could not parse statement to few elements returning 0");
			return 0;
		}
		else if(elements.length == 2) {
			if(isInt(elements[1])) {
				z = Integer.parseInt(elements[1]);
			}
			else {
				System.out.println("Could not parse statement second element not int returning 0");
				return 0;
			}
		}
		else {
			if(isInt(elements[0])) {
				x= Integer.parseInt(elements[0]);
			}
			else {
				System.out.println("Could not parse statement first element not int returning 0");
				return 0;
			}
			if(isInt(elements[2])) {
				y= Integer.parseInt(elements[2]);
			}
			else {
				System.out.println("Could not parse statement third element not int returning 0");
				return 0;
			}
			
		}
		
		//switch statement to send expression to proper method for evaluation
		switch (elements[1]){
		case "*":
			return this.multiply(x,y);
		case "/":
			if (Integer.parseInt(elements[2])!=0){
				return this.divide(x,y);
			}
			else {
				System.out.println("cannot divide by 0 returning 0");
				return 0;
			}
		case "+":
			return this.add(x,y);
		case "-":
			return this.subtract(x,y);
		case "^":
			return this.exponent(x,y);
		default:
			if(elements[0].equals("fibi")) {
				return fibonacci(z);
			}
			System.out.println("Could not parse statement unknown issue returning 0");
			return 0;
		}
	}
	
	//blank main method
	public static void main(String[] args) {
		
		
	}
	
}
