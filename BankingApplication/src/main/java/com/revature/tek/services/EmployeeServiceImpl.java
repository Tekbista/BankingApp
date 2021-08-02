package com.revature.tek.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.tek.dao.AccountDAO;
import com.revature.tek.dao.AccountDAOImpl;
import com.revature.tek.dao.TransactionDAO;
import com.revature.tek.dao.TransactionDAOImpl;
import com.revature.tek.models.Account;
import com.revature.tek.models.Transaction;

public class EmployeeServiceImpl implements EmployeeService {
	private AccountDAO accDAO = new AccountDAOImpl();
	private TransactionDAO tranDAO = new TransactionDAOImpl();
	
	public void approveRejectCustomerAccount(int accountId, String status) {
		accDAO.updateUserAccountStatus(accountId, status);
		
	}



	public List<Account> viewCustomerAccount(String firstName, String lastName) {
		List<Account> accounts = new ArrayList<Account>();
		// Get the list of account associated with the customer
		accounts = accDAO.getCustomerAccount(firstName, lastName);
		return accounts;
	}

	public List<Transaction> viewAllTransaction() {
		List<Transaction> transactions = new ArrayList<Transaction>();
		// Get list of transaction
		transactions = tranDAO.getAllTransaction();
		
		return transactions;
	}

}
