package com.revature.tek.controllers;

import java.util.Scanner;

import com.revature.tek.models.User;
import com.revature.tek.services.CustomerService;
import com.revature.tek.services.CustomerServiceImpl;

public class RegistrationController {

	// Private static variables
	private static Scanner scanner = new Scanner(System.in);
	private static User user = new User();
	static CustomerService cusService = new CustomerServiceImpl();
	
	public static void init() {
		displayRegistrationPrompt();
		boolean register = cusService.registerCustomer(user);
		
		if(register) {
			System.out.println("Registration success");
		}else {
			System.out.println("Username already exist, Try with another username.");
		}
		
	}
	
	// Display login banner
	public static void displayRegistrationBanner() {
		System.out.printf("\n****************************************************\n"
				+ "REGISTRATION\n" 
	            + "***************************************************\n");
	}
	
	// Display login prompt
	public static User displayRegistrationPrompt() {
		displayRegistrationBanner();
		
		try {
			System.out.println("\nEnter your first name:\n");
			String firstName = scanner.nextLine();
			System.out.println("\nEnter your last name:\n");
			String lastName = scanner.nextLine();
			System.out.println("\nEnter your username:\n");
			String username = scanner.nextLine();
			System.out.println("\nEnter your password:\n");
			String password = scanner.nextLine();
			
			user.setUsername(username);
			user.setPassword(password);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			
		}catch(Exception ex) {
			System.out.println("\nError: Invalid input!");
		}
		
		return user;
	}
}
