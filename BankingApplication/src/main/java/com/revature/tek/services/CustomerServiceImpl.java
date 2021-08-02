package com.revature.tek.services;


import java.util.ArrayList;
import java.util.List;

import com.revature.tek.dao.AccountDAO;
import com.revature.tek.dao.AccountDAOImpl;
import com.revature.tek.dao.TransactionDAO;
import com.revature.tek.dao.TransactionDAOImpl;
import com.revature.tek.dao.UserDAO;
import com.revature.tek.dao.UserDAOImpl;
import com.revature.tek.models.Account;
import com.revature.tek.models.Transaction;
import com.revature.tek.models.User;


public class CustomerServiceImpl implements CustomerService {

	UserDAO userDAO = new UserDAOImpl();
	AccountDAO accDAO = new AccountDAOImpl();
	TransactionDAO transDAO = new TransactionDAOImpl();
	
	
	public boolean registerCustomer(User user) {
		
		if(!userDAO.isUserExist(user.getUsername())) {
			user.setRoleId(2);
			return userDAO.addUser(user);
		}else {
			return false;
		}
		
	}
	
	public String applyNewBankAccount( Account account) {
		String accountNum = "";
		account.setAccountNumber(account.generateAccountNumber());
		
		accountNum = accDAO.createAccount(account);
		
		return accountNum;
	}

	public List<Account> viewAccountBalance(int userId) {
		List<Account> accounts = new ArrayList<Account>();
		accounts = accDAO.getUserAccounts(userId);
		
		return accounts;
	}

	// Withdraw the money from account
	public double withdrawMoney(int userId, int accountId, Transaction transaction) {
		double newBalance = 0;
		
		newBalance = accDAO.withdraw(userId, transaction.getTransactionAmount());
		transDAO.saveTransaction(userId, accountId, transaction);
		
		return newBalance;
	}
	
	// Deposit money to the account
	public double depositeMoney(int userId, int accountId, Transaction transaction) {
		double newBalance = 0;
		
		newBalance = accDAO.deposit(userId, transaction.getTransactionAmount());
		transDAO.saveTransaction(userId, accountId, transaction);
		
		return newBalance;
	}

	// Transfer money
	public String transferMoney(String from, String to, double amount) {
		
		return null;
	}

	public void acceptTransferMoney() {
		
		
	}



}
