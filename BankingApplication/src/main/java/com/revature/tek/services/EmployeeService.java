package com.revature.tek.services;

import java.util.List;

import com.revature.tek.models.Account;
import com.revature.tek.models.Transaction;

public interface EmployeeService {

	void approveRejectCustomerAccount(int accountId, String status);
	List<Account> viewCustomerAccount(String firstName, String lastName);
	List<Transaction> viewAllTransaction();
}
