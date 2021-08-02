package com.revature.tek.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.tek.dao.AccountDAO;
import com.revature.tek.dao.AccountDAOImpl;
import com.revature.tek.models.Account;
import com.revature.tek.models.AccountType;
import com.revature.tek.models.Transaction;
import com.revature.tek.services.CustomerService;
import com.revature.tek.services.CustomerServiceImpl;

public class CustomerServiceController {

	public static Scanner scanner = new Scanner(System.in);
	public static boolean runner = true;
	private static AccountDAO accDAO = new AccountDAOImpl();
	private static CustomerService custService = new CustomerServiceImpl();
	
	public static void init() {
		
		while(runner) {
			int option = displayCustomerServiceMenu();
			
			switch(option) {
			case 1: // Call apply new bank account function
				displayApplyNewBankAccount();
				break;
				
			case 2: // Call view account balance function
				displayViewAccountBalance();
				break;
				
			case 3: // Call make deposit function
				displayMakeDeposite();
				break;
				
			case 4: // Call make withdraw function
				displayWithdrawMoney();
				break;
			case 5: // Call money transfer function
				displayMoneyTransfer();
				break;
			case 6: // Call logout function
				runner = false;
				break;
				
			}
		}
	}
	
	
	// Display Employee Service Banner
	public static void displayCustomerServiceBanner() {
		System.out.printf("\n****************************************************\n"
				+ "CUSTOMER SERVICES\n" 
	            + "***************************************************\n");
	}
	
	// Display account type banner
	public static void displayAccountTypeBanner() {
		System.out.printf("\n****************************************************\n"
				+ "ACCOUNT TYPES\n" 
	            + "***************************************************\n");
	}
	
	// Display employee service menu
	public static int displayCustomerServiceMenu() {
		displayCustomerServiceBanner();
		
		System.out.println("\n1 FOR APPLY NEW BANK ACCOUNT \n2 FOR VIEW BALANCE \n3 FOR MAKE DEPOSIT "
				+ "\n4 FOR MAKE WIDTHDRAWL \n5 FOR TRANSFER MONEY \n6 FOR LOGOUT");
		System.out.println("\nSelect an option from the above menu.");
		int option = 0;
		
		try {
			option = scanner.nextInt();
			
		}catch(Exception ex) {
			System.err.println("Errro: Invalid entry!");
		}
		
		return option;
	}
	
	// Apply new bank account for customer
	public static void displayApplyNewBankAccount() {
		boolean runner = true;
		
		while(runner) {
			int option = displayAllAccountTypes();
			
			switch(option) {
			case 1: // Create checking account
				createAccount(1);
				break;
			case 2: // Create saving account
				createAccount(2);
				break;
			case 3: // Create business account
				createAccount(3);
				break;
			case 4: // quit
				runner = false;
				break;
			}
		}
		
	}
	
	// Display customer account balance
	public static void displayViewAccountBalance() {
		List<Account> accounts = new ArrayList<Account>();
		accounts = custService.viewAccountBalance(LoginController.user.getUserId());
		System.out.printf("\n****************************************************\n"
				+ "ACCOUNT BALANCE\n" 
	            + "***************************************************\n");
		
		if(accounts.size() > 0) {
			for(Account acc: accounts) {
				System.out.println(acc.getAccountId() + " | " + acc.getAccountType() + " | $" + acc.getBalance());
			}
		}else {
			System.out.println("No account found for the user.");
		}
	}
	
	
	// Display customer account balance
	public static int displayCustomerAccount() {
		int option = 0;
		List<Account> accounts = new ArrayList<Account>();
		accounts = custService.viewAccountBalance(LoginController.user.getUserId());
		System.out.printf("\n****************************************************\n"
				+ "ACCOUNT \n" 
	            + "***************************************************\n");
		
		if(accounts.size() > 0) {
			for(Account acc: accounts) {
				System.out.println(acc.getAccountId() + ". " + acc.getAccountType() + "(" + acc.getAccountNumber() + ")");
			}
			System.out.println("0. Exit");
		}else {
			System.out.println("No account found for the user.");
		}
		
		System.out.println("Select an account for transaction");
		try {
			option = scanner.nextInt(); // Get the input from user
				
		}catch(Exception ex) {
			System.err.println("Error: Invalid entry!");
		}
		
		return option;
	}
	
	// Display make deposit
	public static void displayMakeDeposite() {
		boolean runner = true;
		
		while(runner) {
			int option = displayCustomerAccount();
			
			switch(option) {
			case 1: // Deposit to the checking account
				makeDeposit(1);
				break;
			case 2: // Deposit to the saving account
				makeDeposit(2);
				break;
			case 3: // Deposit to the business account
				makeDeposit(3);
				break;
			case 0: // quit
				runner = false;
				break;
			}
		}
	}
	
	// Display withdraw money
	public static void displayWithdrawMoney() {
		boolean runner = true;
		
		while(runner) {
			int option = displayCustomerAccount();
			
			switch(option) {
			case 1: // Deposit to the checking account
				makeWithdraw(1);
				break;
			case 2: // Deposit to the saving account
				makeWithdraw(2);
				break;
			case 3: // Deposit to the business account
				makeWithdraw(3);
				break;
			case 0: // quit
				runner = false;
				break;
			}
		}
	}
	
	// Display money transfer
	public static void displayMoneyTransfer() {
		System.out.println("This is money transfer page");
	}
	
	// Display all account types
	public static int displayAllAccountTypes() {
		List<AccountType> accountTypes = new ArrayList<AccountType>();
		accountTypes = accDAO.getAllAccountType(); // Fetch all the account types 
		displayAccountTypeBanner();
		int option = 0;
		
		for(AccountType accType: accountTypes) {
			System.out.println(accType.getAccountTypeId() + ". " + accType.getAccountType());
		}
		System.out.println("4. Quit");
		System.out.println("\nSelect an option from the above menu.");
		
		try {
			option = scanner.nextInt(); // Get the input from user
				
		}catch(Exception ex) {
			System.err.println("Error: Invalid entry!");
		}
		
		return option;
	}
	
	// Create new account for customer
	public static void createAccount(int accoutTypeId) {
		Account account = new Account();
		double amount = 0;
		System.out.println("Enter the initial amount you want to deposite: ");
		
		try {
			amount = scanner.nextDouble();
		}catch(Exception ex) {
			System.err.println("Error: Invalid entry!");
		}
		
		account.setBalance(amount);
		account.setAccountStatus("PENDING");
		account.setAccountTypeId(accoutTypeId);
		account.setUserId(LoginController.user.getUserId());
		String accNum = custService.applyNewBankAccount(account);
		if(!accNum.isEmpty()) {
			System.out.println("Account created successfully.");
			System.out.println("Account Number: " + accNum + "| Account Status: PENDING" +
			                    "\nPlease allow 24 hours to APPROVE or DECLINE your account");
		}else {
			System.err.println("Error: Could not creat your account");
		}
	}
	
	
	
	// make deposit to a specific account
	public static void makeDeposit(int accountId) {
		Transaction trans = new Transaction();
		int userId = LoginController.user.getUserId();
		double amount = 0;
		
		System.out.println("Enter the amount you want to deposit");
		try {
			amount = scanner.nextDouble();
		}catch(Exception ex) {
			System.err.println("Error: Invalid entry!");
		}
		
		trans.setAccountId(accountId);
		trans.setTransactionAmount(amount);
		trans.setTransactionDate(trans.recordTransactionDate());
		trans.setTransactionStatus("APPROVED");
		trans.setTransectionTypeId(2);
		
		System.out.println("Deposite success. Your new account balance is $" 
		                    + custService.depositeMoney(userId, accountId, trans));
		
		
	}
	
	// make deposit to a specific account
	public static void makeWithdraw(int accountId) {
		Transaction trans = new Transaction();
		int userId = LoginController.user.getUserId();
		double amount = 0;
		
		System.out.println("Enter the amount you want to withdraw");
		try {
			amount = scanner.nextDouble();
		}catch(Exception ex) {
			System.err.println("Error: Invalid entry!");
		}
		
		if(accDAO.getAccountBalanceById(accountId) > amount) {
			trans.setAccountId(accountId);
			trans.setTransactionAmount(amount);
			trans.setTransactionDate(trans.recordTransactionDate());
			trans.setTransactionStatus("APPROVED");
			trans.setTransectionTypeId(2);
			
			System.out.println("Withdraw success. Your new account balance is $" 
			                    + custService.withdrawMoney(userId, accountId, trans));
		}else {
			System.out.println("Transaction rejected! You do not have sufficient fund in your account.");
		}
		
		
		
		
	}
	
	
}
