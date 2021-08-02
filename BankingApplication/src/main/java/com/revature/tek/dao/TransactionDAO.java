package com.revature.tek.dao;

import java.util.List;

import com.revature.tek.models.Transaction;

public interface TransactionDAO {

	void saveTransaction(int userId, int accountId, Transaction transaction);
	void updateTransaction(int transactionId, String status);
	List<Transaction> getAllTransaction();
}
