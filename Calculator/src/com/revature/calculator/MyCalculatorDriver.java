package com.revature.calculator;

import java.util.Scanner;


public class MyCalculatorDriver {
	
	public static void main(String[] args) {
		//initiate instance of myCalculator
		MyCalculator calc = new MyCalculator();
		
		//while loop to repeat code until done
		Boolean keepGoing = true;
		while(keepGoing) {
			//create instance of scanner
			Scanner scan= new Scanner(System.in);
			
			//print block for instructions
			System.out.println("Enter an equation with all elements seperated by a sapce");
			System.out.println("use +,-,*,/,^ for operators in a X + Y format");
			System.out.println("to calculate fibonaci enter fibi X");
			System.out.print("Enter text here: ");
			
			//get user input, parse it, calculate the expression and print results
			String userInput = scan.nextLine();
			System.out.println(userInput+ " = "+calc.parse(userInput));
			
			//check if user wants to continue
			int holder = 1;
			while(holder == 1) {
				System.out.print("Continue (Y/N): ");
				String cont = scan.next();
				if (cont.equals("Y")) {
					holder = 0;
				}
				else if (cont.equals("N")) {
					holder = 0;
					keepGoing = false;
				}
				else {
					System.out.println("Invalid entry");
				}
			}
			
		}
		System.out.println("goodbye");
	}
}
