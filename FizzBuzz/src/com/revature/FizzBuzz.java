package com.revature;

import java.util.Scanner;



public class FizzBuzz {
	//main method to test the FizzBuzz
	public static void main(String[] args) {
		int tester = 1;
		
		while (tester != 0) {
			Scanner scan = new Scanner(System.in);
			
			System.out.print("Enter Test Number (enter 0 to end): ");
			
			tester = scan.nextInt();
			
			FizzBuzz(tester);
		}
	}
	
	//fizzbuzz method
	public static void FizzBuzz(int a) {
		for(int i = 1; i <= a; ++i) {
			if( i % 3 == 0) {
				if( i % 5 == 0) {
					System.out.println("FizzBuzz");
				}
				else {
					System.out.println("Fizz");
				}
			}
			else if( i % 5 == 0) {
				System.out.println("Buzz");
			}
			else {
				System.out.println(i);
			}
		}
	}
}
