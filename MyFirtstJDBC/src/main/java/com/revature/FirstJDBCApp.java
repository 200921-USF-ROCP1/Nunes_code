package com.revature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FirstJDBCApp {
	public static void main(String[] args) {
		String connectionString = "jdbc:postgresql://lallah.db.elephantsql.com:5432/jdxdkodz",
				userName = "jdxdkodz",
				password = "bRjjIa0wtoV4jxmEwFEsn5Rqo_lg_beK";
		
		Connection  connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			
			connection = DriverManager.getConnection(connectionString,userName,password);
			
			regularStatement(connection);
			
			preparedStatment(connection);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public static void regularStatement(Connection conncection) throws SQLException{
		Statement stmt = conncection.createStatement();
		ResultSet rs = stmt.executeQuery("select * from residents where last_name = 'Davis';");
		
		while (rs.next()) {
			System.out.println(rs.getString(1) + " " + rs.getString("last_name"));
		}
	}
	public static void preparedStatment(Connection connection) throws SQLException{
		PreparedStatement ps = connection.prepareStatement("select * from residents where id =?");
		ps.setInt(1, 2);;
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString("first_name") + " " + rs.getString("last_name"));
		}
		}
} 
