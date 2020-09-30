package com.polymorphism.animals;

public class Animal {
	int age;
	String name;
	public Animal() {
		age=0;
		name="unknown";
	}
	
	//get and set methods- note these will not need to be overridden
	public int getAge() {
		return age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	//these methods will be overridden
	public void makeNoise() {
		System.out.println("NOISE!!!");
	}
	public void whatsMyDiet() {
		System.out.println("FOOD!!!");
	}
}
