package com.revature.tek.services;


import com.revature.tek.dao.UserDAO;
import com.revature.tek.dao.UserDAOImpl;
import com.revature.tek.models.User;

public class UserServiceImpl implements UserService {

	UserDAO userDAO = new UserDAOImpl();

	public User loginUser(String username, String password) {
		
		User user = new User();
		user = userDAO.getUserByUsername(username);
		
		return user;
	}


}
