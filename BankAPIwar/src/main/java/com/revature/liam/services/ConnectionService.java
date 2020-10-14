package com.revature.liam.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionService {
	private static Connection connection;
	//Class.forName("org.postgresql.Driver");
	
	
	public static Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
				String url = "jdbc:postgresql://lallah.db.elephantsql.com:5432/eebfrxhs";
				String username = "eebfrxhs";
				String password = "MsqfnR0n9TT_5aViY94-y6GwgKmM4v4w";
				Properties prop = new Properties();
				
				prop.put("url", url);
				prop.put("username", username);
				prop.put("password", password);
				
				connection = DriverManager.getConnection(prop.getProperty("url"), 
						prop.getProperty("username"),prop.getProperty("password"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return connection;
	}
	
	public static void closeConnection() {
		try {
			if (connection != null)
			connection.close();
			connection = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
