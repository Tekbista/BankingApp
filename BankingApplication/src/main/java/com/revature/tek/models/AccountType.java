package com.revature.tek.models;

public class AccountType {

	// Private fields
	private int accountTypeId;
	private String accountType;

	// Default Constructor
	public AccountType() {
		super();

	}

	// Parameterized Constructor
	public AccountType(int accountTypeId) {
		super();
		this.accountTypeId = accountTypeId;
	}
	public AccountType(int accountTypeId, String accountType) {
		super();
		this.accountTypeId = accountTypeId;
		this.accountType = accountType;
	}

	// Getters and Setters
	public int getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(int accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	// to string method
	@Override
	public String toString() {
		return "AccountType [accountTypeId=" + accountTypeId + ", accountType=" + accountType + "]";
	}

}
