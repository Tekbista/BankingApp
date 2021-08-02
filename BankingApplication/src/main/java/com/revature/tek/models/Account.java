package com.revature.tek.models;

import java.util.Random;

public class Account extends AccountType {

	// Private fields
	private int accountId;
	private String accountNumber;
	private double balance;
	private String accountStatus;
	private int userId;

	// Default Constructor
	public Account() {
		super();
	}

	public Account(int accountId, String accountNumber, double balance, String accountStatus, int userId) {
		super();
		this.accountId = accountId;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.accountStatus = accountStatus;
		this.userId = userId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountNumber() {
		
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
			
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	// Method to generate 10 digit account number.
	public String generateAccountNumber() {
		int m = (int) Math.pow(10, 10 -1);
		Long num = (long) (m + new Random().nextInt(9 * m));
		String accountNumber = num.toString();
		
		return accountNumber;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountNumber=" + accountNumber + ", balance=" + balance
				+ ", accountStatus=" + accountStatus + ", userId=" + userId + "]";
	}


	
}
