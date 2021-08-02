package com.revature.tek.controllers;

import java.util.Scanner;

import com.revature.tek.dao.UserDAO;
import com.revature.tek.dao.UserDAOImpl;
import com.revature.tek.models.User;
import com.revature.tek.services.UserService;
import com.revature.tek.services.UserServiceImpl;

public class LoginController {
	// Private static variable
	private static Scanner scanner = new Scanner(System.in);
	public static User user = new User();
	static UserService userService = new UserServiceImpl();
	static UserDAO userDAO = new UserDAOImpl();

	public static void init() {
		displayLoginPrompt();
		
	}
	
	
	// Display login banner
	public static void displayLoginBanner() {
		System.out.printf("\n****************************************************\n"
				+ "LOGIN\n" 
	            + "***************************************************\n");
	}
	
	// Display login prompt
	public static void displayLoginPrompt() {
		displayLoginBanner();
		
		try {
			System.out.println("Enter your username:");
			String username = scanner.nextLine();
			System.out.println("Enter your password:");
			String password = scanner.nextLine();
			
			if(userDAO.isUserExist(username)) {
				user = userService.loginUser(username, password);
				if(user.getRoleId() == 1) {
					EmployeeServiceController.init();
				}else {
					CustomerServiceController.init();
				}
			}else {
				System.err.println("Invalid login. Please check you login credential");
			}
			
		}catch(Exception ex) {
			System.err.println("\nError: Invalid input!");
		}
		
	}
	
	
	
}
