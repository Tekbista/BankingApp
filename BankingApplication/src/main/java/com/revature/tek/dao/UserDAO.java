package com.revature.tek.dao;

import com.revature.tek.models.User;

public interface UserDAO {

	// CRUD operations on user
	
	// Add user
	boolean addUser(User user);
	
	// Read User(s)
	User getUserByUsername(String username);
	boolean isUserExist(String username);
	
}
