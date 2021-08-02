package com.revature.tek.dao;

import java.util.List;

import com.revature.tek.models.Account;
import com.revature.tek.models.AccountType;

public interface AccountDAO {
	
	// CRUD operations on account
	
	// Create account
	String createAccount(Account account);
	
	// Read account(s)
	List<Account> getUserAccounts(int id);
	List<Account> getListOfPendingAccount();
	double getAccountBalance(int userId, int accountTypeId);
	double getAccountBalanceById(int accountId);
	List<AccountType> getAllAccountType();
	List<Account> getCustomerAccount(String firstName, String lastName);
	
	// Update account
	void updateUserAccountStatus(int accountId, String status);
	double deposit(int accountId, double depositeAmount);
	double withdraw(int accountId, double depositeAmount);

}
