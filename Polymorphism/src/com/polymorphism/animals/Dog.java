package com.polymorphism.animals;

public class Dog extends Animal{
	//overloading constructors
	public Dog() {
		this.setAge(0);
		this.setName("unknown");
	}
	public Dog(int age) {
		this.setAge(age);
		this.setName("unknown");
	}
	public Dog(String name) {
		this.setAge(0);
		this.setName(name);
	}
	public Dog(int age,String name) {
		this.setAge(age);
		this.setName(name);
	}
	
	
	//override examples
	public void makeNoise() {
		System.out.println("WOOF!!!");
	}
	public void whatsMyDiet() {
		System.out.println("Dog Food");
	}
}
