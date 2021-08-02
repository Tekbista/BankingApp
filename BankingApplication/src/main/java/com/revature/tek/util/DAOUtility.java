package com.revature.tek.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOUtility {

	private static String CONNECTION_USERNAME;
	private static String CONNECTION_PASSWORD;
	private static String CONNECTION_URL;
	private static Connection connection;
	
	// static initialization block: 
		// need to be outside of methods, inside of class
		// code executed only once (whenever class is loaded into memory)
		// why are we using it? to initialize our static properties once we finally get to our sensitive data
	static {
		try {
			// get reference to properties file
			DAOUtility.class.getClassLoader();
			InputStream input = ClassLoader.getSystemResourceAsStream("config.properties");
			Properties properties = new Properties();
			properties.load(input);
			
			
			// Load the database credentials from environmental variables 
			CONNECTION_USERNAME = System.getenv(properties.getProperty("CONNECTION_USERNAME"));
			CONNECTION_PASSWORD = System.getenv(properties.getProperty("CONNECTION_PASSWORD"));
			CONNECTION_URL = System.getenv(properties.getProperty("CONNECTION_URL"));
			
			input.close();
		} catch(IOException ex) {
			System.out.println("Failed to load properties from file.");
			ex.printStackTrace();
		}
	}
	
	// Get the database connection
	public static Connection getConnection() throws SQLException {
		try {
			// Load the driver to connect with the database
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException ex) {
			System.out.println("Could not register driver!");
			ex.printStackTrace();
		}
		
		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(CONNECTION_URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		
		return connection;
	}
	

	// Close the database connection
	public static void closeConnection(){
		try {
			// If the connection is not null and open, close the connection
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
			
		}catch(SQLException e) {
			System.out.println("Error: Could not close connection.");
			e.printStackTrace();
		}
		
		
		
	}
}
