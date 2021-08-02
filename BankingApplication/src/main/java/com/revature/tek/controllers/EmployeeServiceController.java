package com.revature.tek.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.tek.dao.AccountDAO;
import com.revature.tek.dao.AccountDAOImpl;
import com.revature.tek.models.Account;
import com.revature.tek.models.Transaction;
import com.revature.tek.services.EmployeeService;
import com.revature.tek.services.EmployeeServiceImpl;

public class EmployeeServiceController {

	public static Scanner scanner = new Scanner(System.in);
	public static boolean runner = true;
	private static AccountDAO accDAO = new AccountDAOImpl();
	private static EmployeeService empService = new EmployeeServiceImpl();
	
	public static void init() {
		
		while(runner) {
			int option = displayEmployeeServiceMenu();
			
			switch(option) {
			case 1:
				displayApproveRejectAccount();
				break;
			case 2: // View a customer's bank account
				displayCustomerBankAccount();
				break;
				
			case 3: // View all log of transaction
				displayAllTransactions();
				break;
				
			case 4: // Quit the application
				runner = false;
				break;
				
			}
		}
	}
	
	
	// Display Employee Service Banner
	public static void displayEmployeeServiceBanner() {
		System.out.printf("\n****************************************************\n"
				+ "                EMPLOYEE SERVICES\n" 
	            + "***************************************************\n");
	}
	
	// Display employee service menu
	public static int displayEmployeeServiceMenu() {
		displayEmployeeServiceBanner();
		
		System.out.println("\n1 TO APPROVE/REJECT CUSTOMER ACCOUNT \n2 TO VIEW CUSTOMER BANK ACCOUNT "
				          + "\n3 TO VIEW ALL TRANSACTION \n4 TO EXIT ");
		System.out.println("\nSelect an option from the above menu.");
		int option = 0;
		
		try {
			String input = scanner.nextLine();
			option = Integer.parseInt(input);
			
		}catch(Exception ex) {
			System.out.println("Errro: Invalid entry!");
		}
		
		return option;
	}
	
	
	public static void displayApproveRejectAccount() {
		int accountId = displayAllNewAccountApplicatioin();
		int option = 0;
		
		System.out.println("ENTER 1 TO  APPROVE ACCOUNT, 2 TO REJECT ACCOUNT");
		try {
			option = scanner.nextInt();
		}catch(Exception ex) {
			System.out.println("Errro: Invalid entry!");
		}
		
		switch(option) {
		case 1:// Approve customer account
			empService.approveRejectCustomerAccount(accountId, "ACTIVE");
			System.out.println("Account you have selected is approved.");
			break;
			
		case 2:// Reject customer account
			empService.approveRejectCustomerAccount( accountId, "REJECTED");
			System.out.println("Account you have selected is rejected.");
			break;
			
		}
	}
	
	// Display customer bank account
	public static void displayCustomerBankAccount() {
		List<Account> accounts = new ArrayList<Account>();
		String firstName = "";
		String lastName = "";
		
		try {
			System.out.println("Enter the customer first name:");
			firstName = scanner.nextLine().trim();
			System.out.println("Enter the customer first name:");
			lastName = scanner.nextLine().trim();
		}catch(Exception ex) {
			System.out.println("Errro: Invalid entry!");
		}
		
		customerAccountBanner();
		accounts = empService.viewCustomerAccount(firstName, lastName);
		System.out.println("ID \t ACCOUNT TYPE \t ACCOUNT NUMBER \t BALANCE \t ACCOUNT STATUS");
		
		if(accounts.size() > 0) {
			for(Account acc: accounts) {
				// display all the account associated with a customer
				System.out.println(acc.getAccountId() + "\t " + acc.getAccountType() + "\t " + acc.getAccountNumber() + 
						           "\t   \t" + acc.getBalance() + "\t \t" + acc.getAccountStatus());
			}
		}else {
			System.out.println("Acount not found for the customer");
		}
		
	}
	
	// Display account transaction
	public static void displayAllTransactions() {
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions = empService.viewAllTransaction();
		
		transactionBanner();
		System.out.println("ID\t  ACCOUNT TYPE\t ACCOUNT TYPE ID\t TYPE\t\t AMOUNT\t\t STATUS\t\t DATE");
		for(Transaction trans: transactions) {
			System.out.println(trans.getTransectionId() + "\t\t" + trans.getAccountId() + "\t\t" + trans.getAccountTypeID()
								+ "\t\t" + trans.getTransectionType() + "\t " + trans.getTransactionAmount() + "\t\t" 
					            + trans.getTransactionStatus() + "\t" + trans.getTransactionDate());
		}
		
	}
	
	// Display all new account application
	public static int displayAllNewAccountApplicatioin() {
		List<Account> accounts = new ArrayList<Account>();
		accounts = accDAO.getListOfPendingAccount();
		int accountId = 0;
		
		ApproveRejectAccountBanner(); // Display the banner
		
		System.out.println("ID\t ACCOUNT TYPE\t ACCOUNT NUMBER\t BALANCE\t ACCOUNT STATUS");
		if(accounts.size() > 0) {
			for(Account acc: accounts) {
				// display all the account associated with a customer
				System.out.println(acc.getAccountId() + "\t " + acc.getAccountType() + "\t " + acc.getAccountNumber() 
				                   + "\t \t" + acc.getBalance() + "\t \t" + acc.getAccountStatus());
			}
			System.out.println("Enter an ID to select an account you want to APPROVE OR REJECT: ");
			
			try {
				accountId = scanner.nextInt();
			}catch(Exception ex) {
				System.out.println("Errro: Invalid entry!");
			}
		}else {
			System.out.println("No record is found.");
		}
		
		
		return accountId;
	}
	
	public static void ApproveRejectAccountBanner() {
		System.out.printf("\n****************************************************\n"
				+ "            APPROVE OR REJECT ACCOUNT\n" 
	            + "***************************************************\n");
	}
	
	public static void customerAccountBanner() {
		System.out.printf("\n****************************************************\n"
				+ "                   LIST OF ACCOUNT\n" 
	            + "***************************************************\n");
	}
	
	public static void transactionBanner() {
		System.out.printf("\n****************************************************\n"
				+ "                LIST OF TRANSACTION\n" 
	            + "***************************************************\n");
	}
}
