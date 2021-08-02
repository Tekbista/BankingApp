package com.revature.tek.controllers;

import java.util.Scanner;

public class MainController {

	public static Scanner scanner = new Scanner(System.in);
	public static boolean runner = true;
	
	
	public static void init() {
		
		while(runner) {
			int option = displayMainMenu();
			
			switch(option) {
			case 1: // Call login service layer function
				LoginController.init();
				break;
				
			case 2: // Call registration service layer function
				RegistrationController.init();
				break;
				
			case 3: // Quit the application
				runner = false;
				displayGoodbyeMessage();
				break;
				
			}
		}
		
	}
	
	// Application Banner
	public static void displayAppBanner() {
		System.out.printf("\n****************************************************\n"
				+ "WELCOME TO THE BANKING APPLICATION\n" 
	            + "***************************************************\n");
	}
	
	
	// Display goodbye message
	public static void displayGoodbyeMessage() {
		System.out.println("Thank you for using our service.");
	}
	
	
	// Display main menu 
	public static int displayMainMenu() {
		displayAppBanner();
		
		System.out.println("1. FOR LOGIN\n2. For REGISTRATION\n3. FOR QUIT");
		System.out.println("\nSelect an option from the above menu.");
		int option = 0;
		
		try {
			String input = scanner.nextLine();
			option = Integer.parseInt(input);
			
		}catch(Exception ex) {
			System.out.println("\nError: Invalid input!");
		}
		
		return option;
	}
}
