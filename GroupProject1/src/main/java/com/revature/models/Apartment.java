package com.revature.models;

import java.util.List;

public class Apartment {
	//instance variables
	private int id;
	private char buildingLetter;
	private int roomNumber;
	private Float monthly_rent;
	private List<Resident> residents;
	private List<Pet> pets;
	
	//getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public char getBuildingLetter() {
		return buildingLetter;
	}
	public void setBuildingLetter(char buildingLetter) {
		this.buildingLetter = buildingLetter;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public Float getMonthly_rent() {
		return monthly_rent;
	}
	public void setMonthly_rent(Float monthly_rent) {
		this.monthly_rent = monthly_rent;
	}
	
	//not sure if this is best way to implement
	public List<Resident> getResidents() {
		return residents;
	}
	public void setResidents(List<Resident> residents) {
		this.residents = residents;
	}
	public List<Pet> getPets() {
		return pets;
	}
	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}
}
