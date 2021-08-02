package com.revature.tek.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.tek.models.User;
import com.revature.tek.util.DAOUtility;

public class UserDAOImpl implements UserDAO{
	private Connection connection;
	private PreparedStatement statement;
	

	public boolean addUser(User user) {
		boolean result = false;
		
		try {
			connection = DAOUtility.getConnection();
			String sql = "INSERT INTO users" + "(first_name, last_name, username, password, role_id) VALUES" + "(?,?,?,?,?) LIMIT 1;";
			statement = connection.prepareStatement(sql);
			statement.setString(1, user.getFirstName()); 
			statement.setString(2, user.getLastName()); 
			statement.setString(3, user.getUsername()); 
			statement.setString(4, user.getPassword());
			statement.setInt(5, user.getRoleId());
			
			// Execute the query to save user into users table in database
			statement.executeUpdate();
			result = true;
			// Close the statement
			statement.close();
		} catch (SQLException e) {
			System.out.println("Error: User could not added.");
			e.printStackTrace();
		}finally {
			DAOUtility.closeConnection();
		}
		
		return result;
	}

	// Get user by username
	public User getUserByUsername(String username) {
		User user = new User();
		try {
			connection = DAOUtility.getConnection();
			final String sql = "SELECT * FROM users WHERE username = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			
			// Execute the query to fetch user detail from users table in database
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				result.getRow();
				
				user.setUserId(result.getInt(1));
				user.setFirstName(result.getString(2));
				user.setLastName(result.getString(3));
				user.setUsername(result.getString(4));
				user.setPassword(result.getString(5));
				user.setRoleId(result.getInt(6));
			}
			// Close the statement
			statement.close();
		}catch(SQLException e) {
			System.out.println("Error: Could not load user info");
			e.printStackTrace();
		}finally {
			DAOUtility.closeConnection();
		}
		return user;
	}

	// Check if user exist 
	public boolean isUserExist(String username) {
		boolean userExist = false;
		try {
			connection = DAOUtility.getConnection();
			final String sql = "SELECT * FROM users WHERE username = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			
			// Execute the query to fetch user detail from users table in database
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				userExist = true;
			
			}
			// Close the statement
			statement.close();
		}catch(SQLException e) {
			System.out.println("Error: User not found");
			e.printStackTrace();
		}finally {
			DAOUtility.closeConnection();
		}
		return userExist;
	}

}
