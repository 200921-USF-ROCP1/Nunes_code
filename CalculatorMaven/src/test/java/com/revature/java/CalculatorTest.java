package com.revature.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.revature.calculator.MyCalculator;

public class CalculatorTest {
	
	@Test
	public void test1() {
		//set up 
		MyCalculator calc = new MyCalculator();
		int a=3, b=2;
		
		int testAnswer = 5;
		int actualAnswer = calc.add(a, b);
		Assertions.assertEquals(testAnswer, actualAnswer);
		
	}
}
