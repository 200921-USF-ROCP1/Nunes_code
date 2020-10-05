package com.revature;

import java.sql.Connection;
import java.sql.DriverManager;

import com.revature.impementations.RedisentDaoImpl;
import com.revature.interfaces.ResidentDAO;
import com.revature.models.Resident;
import com.revature.services.ConnectionService;

public class App {
	public static void main (String[] args) {
		Connection  connection = null;
		try {
			
			
			ResidentDAO  residentDAO = new RedisentDaoImpl (connection);
			Resident res = new Resident();
			res.setFirstName("Liam");
			res.setLastName("Nunes");
			res.setApartmentID(1);
			
			residentDAO.createResident(res);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			ConnectionService.closeConnection();
	}
	
	
}
