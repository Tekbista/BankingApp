package com.revature.tek.services;

import java.util.List;

import com.revature.tek.models.Account;
import com.revature.tek.models.Transaction;
import com.revature.tek.models.User;

public interface CustomerService {

	boolean registerCustomer(User user);
	String applyNewBankAccount(Account account);
	List<Account> viewAccountBalance(int userId);
	double withdrawMoney(int userId, int accountId, Transaction transaction);
	double depositeMoney(int userId, int accountId, Transaction transaction);
	String transferMoney(String from, String to, double amount);
	void acceptTransferMoney();
	
}
