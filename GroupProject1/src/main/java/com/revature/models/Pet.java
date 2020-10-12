package com.revature.models;

public class Pet {
	//instance variables
	private int id;
	private String breed, name;
	private Apartment apartment;
	private boolean isServiceAnimal;
	
	//getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Apartment getApartment() {
		return apartment;
	}
	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}
	public boolean isServiceAnimal() {
		return isServiceAnimal;
	}
	public void setServiceAnimal(boolean isServiceAnimal) {
		this.isServiceAnimal = isServiceAnimal;
	}
}
