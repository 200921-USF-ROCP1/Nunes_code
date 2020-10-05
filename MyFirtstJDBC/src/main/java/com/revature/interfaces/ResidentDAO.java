package com.revature.interfaces;

import java.util.List;

import com.revature.models.Resident;

public interface ResidentDAO {
	//classic Dao has 4 basic operations: create/add ; retrieve/get; update; delete
	
	public void createResident(Resident resident);
	public Resident getResidentByID(int id);
	
	public void updateRedisent(Resident resident);
	
	public void deleteResident(Resident resident);
	
	//not a part of basic CRUD ops
	public List<Resident> getAllResidents();
	
}
