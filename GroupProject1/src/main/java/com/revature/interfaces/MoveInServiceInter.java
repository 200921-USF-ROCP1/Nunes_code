package com.revature.interfaces;

public interface MoveInServiceInter {
	public void addResident(String firstname, String LastName, int apartNum);
	public void addPet(String breed, String name, String spartmentID, Boolean isServiceAnimal);
	public void addCar(String make, String Model, int year, String plateNumber, int ownerId);
}
