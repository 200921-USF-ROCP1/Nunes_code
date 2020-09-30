package com.polymorphism.animals;

public class Cat extends Animal{
	//overloading constructors
	public Cat() {
		this.setAge(0);
		this.setName("unknown");
	}
	public Cat(int age) {
		this.setAge(age);
		this.setName("unknown");
	}
	public Cat(String name) {
		this.setAge(0);
		this.setName(name);
	}
	public Cat(int age,String name) {
		this.setAge(age);
		this.setName(name);
	}
	
	
	//override examples
	public void makeNoise() {
		System.out.println("MEOW!!!");
	}
	public void whatsMyDiet() {
		System.out.println("Cat Food");
	}
}

