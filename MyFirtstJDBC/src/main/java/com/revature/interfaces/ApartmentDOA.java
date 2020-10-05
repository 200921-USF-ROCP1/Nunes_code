package com.revature.interfaces;

import java.util.List;

import com.revature.models.Apartment;
import com.revature.models.Resident;

public interface ApartmentDOA {
	public void createApartment(Apartment apartment);
	
	public Apartment getApartmentByID(int id);
	
	public void updateApartment(Apartment apartment);
	
	public void deleteApartment(Apartment apartment);
	
	//not a part of basic CRUD ops
	public List<Resident> getAllApartments();
}
