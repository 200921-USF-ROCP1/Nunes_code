package com.revature.interfaces;

import java.util.List;

import com.revature.models.Apartment;
import com.revature.models.Pet;

public interface ManagerServiceInter {
	public List<Apartment> getVacantApartments();
	public List<Pet> getPets();
	public float getGrossMonthlyIncome();
}
