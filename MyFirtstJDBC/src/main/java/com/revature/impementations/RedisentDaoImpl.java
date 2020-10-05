package com.revature.impementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.interfaces.ResidentDAO;
import com.revature.models.Apartment;
import com.revature.models.Resident;

public class RedisentDaoImpl implements ResidentDAO{

	Connection connection;
	
	public RedisentDaoImpl(Connection connection) {
		this.connection = connection;
	}
	
	public void createResident(Resident resident) {
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("INSERT INTO residents (first_name, last_name,apartment_id) Values('" 
			+ resident.getFirstName() + "', '" + resident.getLastName() + "', '" + resident.getApartment().getId() + "');");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Resident getResidentByID(int id) {
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM residents WHERE id = ?");
			ps.setInt(1,id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Resident resident = new Resident();
				resident.setId(rs.getInt("id"));
				resident.setFirstName(rs.getString("first_name"));
				resident.setLastName(rs.getString("last_name"));
				//resident.setApartmentID(rs.getInt("apartment_id"));
				
				
				//should go in its own DAO
				PreparedStatement apartStatement = connection.prepareStatement("SELECT * FROM apartments where id ?");
				apartStatement.setInt(1, resident.getApartment().getId());
				ResultSet apartmentRS = apartStatement.executeQuery();
				if(apartmentRS.next()) {
					Apartment apartment = new Apartment();
					apartment.setId(apartmentRS.getInt("id"));
					apartment.setBuildingLetter(apartmentRS.getString("building_letter"));
					apartment.setRoomNumber(apartmentRS.getInt("room_number"));
					apartment.setMonthlyRent(apartmentRS.getDouble("monthly_rent"));
					
					resident.setApartment(apartment);
				}
				//end of what should go in DAO
				
				
				return resident;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateRedisent(Resident resident) {
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE residents FROM residents WHERE id = ?"
					+ "SET first_name = ?, last_name = ?, apartment_id = ?"
					+" WHERE id = ?;");
			ps.setString(1, resident.getFirstName());
			ps.setString(2, resident.getLastName());
			ps.setInt(3, resident.getApartment().getId());
			ps.setInt(4,  resident.getId());
			
			
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void deleteResident(Resident resident) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM residents WHERE id = ?");
			ps.setInt(1, resident.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public List<Resident> getAllResidents() {
		List<Resident> residents = new ArrayList<Resident>();
		
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM resident;");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Resident resident = new Resident();
				resident.setId(rs.getInt("id"));
				resident.setFirstName(rs.getString("first_name"));
				resident.setLastName(rs.getString("last_name"));
				
				PreparedStatement apartStatement = connection.prepareStatement("SELECT * FROM apartments where id ?");
				apartStatement.setInt(1, resident.getApartment().getId());
				ResultSet apartmentRS = apartStatement.executeQuery();
				if(apartmentRS.next()) {
					Apartment apartment = new Apartment();
					apartment.setId(apartmentRS.getInt("id"));
					apartment.setBuildingLetter(apartmentRS.getString("building_letter"));
					apartment.setRoomNumber(apartmentRS.getInt("room_number"));
					apartment.setMonthlyRent(apartmentRS.getDouble("monthly_rent"));
					
					resident.setApartment(apartment);
				}
				
				residents.add(resident);
			}
			return residents;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
