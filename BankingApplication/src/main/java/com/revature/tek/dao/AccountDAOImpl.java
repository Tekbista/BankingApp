package com.revature.tek.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.tek.models.Account;
import com.revature.tek.models.AccountType;
import com.revature.tek.util.DAOUtility;

public class AccountDAOImpl implements AccountDAO{
	// Private fields
	private Connection connection;
	private PreparedStatement statement;

	// Create new account for user
	public String createAccount(Account account) {
		try {
			connection = DAOUtility.getConnection();
			final String sql = "INSERT INTO accounts" + "(account_number, balance, user_id, account_status, account_type_id) VALUES" + "(?,?,?,?,?) LIMIT 1;"; // building SQL query for insert
			statement = connection.prepareStatement(sql);
			statement.setString(1, account.getAccountNumber());
			statement.setDouble(2, account.getBalance()); 
			statement.setInt(3, account.getUserId());
			statement.setString(4, "PENDING");
			statement.setInt(5, account.getAccountTypeId());
			
			// Execute the query to save customer into customers table in database
			statement.executeUpdate();
			// Close the statement
			statement.close();
			
		}catch(SQLException e) {
			System.out.println("Error: Could not create account");
			e.printStackTrace();
		}finally {
			DAOUtility.closeConnection();
		}
		
		return account.getAccountNumber();
	}

	// Get all the account associated with the user
	public List<Account> getUserAccounts(int id) {
		List<Account> accounts = new ArrayList<Account>();
		
		try {
			connection = DAOUtility.getConnection();
			final String sql = "SELECT * FROM accounts WHERE user_id = ? AND account_status = 'ACTIVE';";

			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Account account = new Account(); 
				account.setAccountId(result.getInt(1));
				account.setAccountNumber(result.getString(2));
				account.setBalance(result.getDouble(3));
				account.setUserId(result.getInt(4));
				account.setAccountStatus(result.getString(5));
				account.setAccountTypeId(result.getInt(6));
				if(account.getAccountTypeId() == 1) {
					account.setAccountType("CHECKING");
				}else if(account.getAccountTypeId() == 2) {
					account.setAccountType("SAVING");
				}else {
					account.setAccountType("BUSINESS");
				}
					
				
				accounts.add(account);
			}
			statement.close();
		} catch (SQLException e) {
			System.out.println("Error: Account not found");
			e.printStackTrace();
		}finally {
			DAOUtility.closeConnection();
		}
		
		return accounts;
	}
	
	
	// Get all the account associated with the customer
	public List<Account> getCustomerAccount(String firstName, String lastName) {
		List<Account> accounts = new ArrayList<Account>();
		
		try {
			connection = DAOUtility.getConnection();
			final String sql = "SELECT * FROM accounts WHERE user_id = (SELECT user_id FROM users WHERE first_name = ? AND last_name = ?);";

			statement = connection.prepareStatement(sql);
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Account account = new Account(); 
				account.setAccountId(result.getInt(1));
				account.setAccountNumber(result.getString(2));
				account.setBalance(result.getDouble(3));
				account.setUserId(result.getInt(4));
				account.setAccountStatus(result.getString(5));
				account.setAccountTypeId(result.getInt(6));
				if(account.getAccountTypeId() == 1) {
					account.setAccountType("CHECKING");
				}else if(account.getAccountTypeId() == 2) {
					account.setAccountType("SAVING  ");
				}else {
					account.setAccountType("BUSINESS");
				}
					
				
				accounts.add(account);
			}
			statement.close();
		} catch (SQLException e) {
			System.out.println("Error: Account not found");
			e.printStackTrace();
		}finally {
			DAOUtility.closeConnection();
		}
		
		return accounts;
	}
	
	
	// Get a list of account with status pending
	public List<Account> getListOfPendingAccount(){
		List<Account> accounts = new ArrayList<Account>();
		
		try {
			connection = DAOUtility.getConnection();
			final String sql = "SELECT * FROM accounts WHERE account_status = 'PENDING';";

			statement = connection.prepareStatement(sql);
			// Execute the query and get the result
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Account account = new Account(); 
				account.setAccountId(result.getInt(1));
				account.setAccountNumber(result.getString(2));
				account.setBalance(result.getDouble(3));
				account.setUserId(result.getInt(4));
				account.setAccountStatus(result.getString(5));
				account.setAccountTypeId(result.getInt(6));
				if(account.getAccountTypeId() == 1) {
					account.setAccountType("CHECKING");
				}else if(account.getAccountTypeId() == 2) {
					account.setAccountType("SAVING");
				}else {
					account.setAccountType("BUSINESS");
				}
					
				
				accounts.add(account);
			}
			statement.close();
		} catch (SQLException e) {
			System.out.println("Error: Account not found");
			e.printStackTrace();
		}finally {
			DAOUtility.closeConnection();
		}
		
		return accounts;
	}
	
	

	// Update an account status
	public void updateUserAccountStatus(int accountId, String status) {
		try {
			connection = DAOUtility.getConnection();
			final String sql = "UPDATE accounts SET account_status = ? WHERE account_id=?;";
			statement = connection.prepareStatement(sql);
			statement.setString(1, status);
			statement.setInt(2, accountId);
			// Execute the query to update the account info in the database
			statement.executeUpdate();
			// Close the statement
			statement.close();
			
		} catch (SQLException e) {
			System.out.println("Error: Account Status could not be updated.");
			e.printStackTrace();
		}finally {
			DAOUtility.closeConnection();
		}
		
	}
	

	// Get all account types
	public List<AccountType> getAllAccountType() {
		List<AccountType> accountTypes = new ArrayList<AccountType>();
		
		try {
			connection = DAOUtility.getConnection();
			final String sql = "SELECT * FROM account_types";

			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next()) {
				AccountType accountType = new AccountType();
				
				accountType.setAccountTypeId(result.getInt(1));
				accountType.setAccountType(result.getString(2));
				
				accountTypes.add(accountType);
				
			}
			statement.close(); // close the statement
			
		} catch (SQLException e) {
			System.out.println("Error: Could not load acccount type");
			e.printStackTrace();
		}finally {
			DAOUtility.closeConnection();
		}
		
		return accountTypes;
	}


	// Get the account balance by userId and accountTypeId
	public double getAccountBalance(int userId, int accountTypeId){
		
		double accBalance = 0;
		
		try {
			connection = DAOUtility.getConnection();
			final String sql = "SELECT balance from accounts WHERE user_id =? AND account_type_id=?;";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, userId);
			statement.setInt(2, accountTypeId);
			
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				accBalance = result.getInt(1);
			}
			statement.close(); // close the statement
		}catch (SQLException e) {
			System.out.println("Error: Could not get account balance");
			e.printStackTrace();
		}finally {
			DAOUtility.closeConnection();
		}
		
		
		return accBalance;
	}
	
	// Update account balance after deposit
	public double deposit(int accountId, double depositeAmount) {
		double newBalance = 0;
		
		try {
			connection = DAOUtility.getConnection();
			final String sql = "UPDATE accounts SET balance = (SELECT balance FROM accounts WHERE account_id = ?) + ?;";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, accountId);
			statement.setDouble(2, depositeAmount);
			
			statement.executeUpdate();
			
			statement.close();
		}catch (SQLException e) {
			System.out.println("Error: Could not get account balance");
			e.printStackTrace();
		}finally {
			DAOUtility.closeConnection();
		}
		
		newBalance = getAccountBalanceById(accountId);
		
		return newBalance;
	}
	
	
	// Update account balance after withdraw
	public double withdraw(int accountId, double depositeAmount) {
		double newBalance = 0;
		
		try {
			connection = DAOUtility.getConnection();
			final String sql = "UPDATE accounts SET balance = (SELECT balance FROM accounts WHERE account_id = ?) - ?;";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, accountId);
			statement.setDouble(2, depositeAmount);
			
			statement.executeUpdate();
			
			statement.close();
		}catch (SQLException e) {
			System.out.println("Error: Could not get account balance");
			e.printStackTrace();
		}finally {
			DAOUtility.closeConnection();
		}
		
		newBalance = getAccountBalanceById(accountId);
		
		return newBalance;
	}
	
	
	// Get account balance by account id
	public double getAccountBalanceById(int accountId) {
		double balance = 0;
		
		try {
			connection = DAOUtility.getConnection();
			final String sql = "SELECT balance FROM accounts WHERE account_id = ?;";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, accountId);
			
			
			ResultSet result = statement.executeQuery();
			result.next();
			balance = result.getDouble(1);
			
			statement.close();
		}catch (SQLException e) {
			System.out.println("Error: Could not get account balance");
			e.printStackTrace();
		}finally {
			DAOUtility.closeConnection();
		}
		
		return balance;
	}
}
