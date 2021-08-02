package com.revature.tek.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.tek.models.Transaction;
import com.revature.tek.util.DAOUtility;

public class TransactionDAOImpl implements TransactionDAO{
	private Connection connection;
	private PreparedStatement statement;

	// Save transaction
	public void saveTransaction(int userId, int accountId, Transaction transaction) {
		try {
			connection = DAOUtility.getConnection();
			final String sql = "INSERT INTO transactions( account_id, account_type_id, transaction_amount, transaction_date, transaction_status, transaction_type_id ) "
					+ "VALUES(?,(SELECT account_type_id FROM accounts WHERE account_id = ?),?,?,?,? );";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, accountId);
			statement.setInt(2, accountId);
			statement.setDouble(3, transaction.getTransactionAmount());
			statement.setString(4, transaction.getTransactionDate());
			statement.setString(5, transaction.getTransactionStatus());
			statement.setDouble(6, transaction.getTransectionTypeId());
			
			
			statement.executeUpdate(); // Execute the query
			
			statement.close(); // close the statement
		} catch (SQLException e) {
			System.out.println("Error: Transaction could not complete.");
			e.printStackTrace();
		}finally {
			DAOUtility.closeConnection();
		}
		
	}

	// Update transaction
	public void updateTransaction(int transactionId, String status) {
		try {
		
			connection = DAOUtility.getConnection();
			final String sql = "UPDATE transactions SET transaction_status =? WHERE transaction_id =?;";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, transactionId);
			statement.setString(2, status);
			
			
			statement.executeUpdate(); // Execute the query
			
			statement.close(); // close the statement
		} catch (SQLException e) {
			System.out.println("Error: Transaction could not complete.");
			e.printStackTrace();
		}finally {
			DAOUtility.closeConnection();
		}
		
	}

	public List<Transaction> getAllTransaction() {
		List<Transaction> transactions = new ArrayList<Transaction>();
		
		try {
			
			connection = DAOUtility.getConnection();
			final String sql = "SELECT transaction_id, transaction_amount, transaction_type_id, transaction_date, "
					          + "transaction_status, account_id, account_type_id FROM transactions;";
			Statement statement = connection.createStatement();
			
			ResultSet result = statement.executeQuery(sql); // Execute the query
			
			while(result.next()) {
				Transaction transaction = new Transaction();
				
				transaction.setTransectionId(result.getInt(1));
				transaction.setTransactionAmount(result.getDouble(2));
				transaction.setAccountTypeID(result.getInt(3));
				if(transaction.getAccountTypeID() == 1) {
					transaction.setTransectionType("Withdraw");
				}else if(transaction.getAccountTypeID() == 2) {
					transaction.setTransectionType("Deposite");
				}else {
					transaction.setTransectionType("Transfer");
				}
				transaction.setTransactionDate(result.getString(4));
				transaction.setTransactionStatus(result.getString(5));
				transaction.setAccountId(result.getInt(6));
				transaction.setAccountTypeID(result.getInt(7));
				
				transactions.add(transaction);// Add transaction to the list
				
			}
			
			statement.close(); // close the statement
		} catch (SQLException e) {
			System.out.println("Error: Transaction could not complete.");
			e.printStackTrace();
		}finally {
			DAOUtility.closeConnection();
		}
		return transactions;
	}
	


}
