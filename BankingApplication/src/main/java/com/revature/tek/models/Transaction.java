package com.revature.tek.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction extends TransactionType {

	// Private Fields
	private int transectionId;
	private int accountId;
	private int accountTypeID;
	private double transactionAmount;
	private String transactionStatus;
	private String transactionDate;

	// Default Constructor
	public Transaction() {
		super();
	}

	// Parameterize constructor
	public Transaction(int transectionId, int accountId, int accountTypeID, double transactionAmount,
			String transactionStatus, String transactionDate) {
		super();
		this.transectionId = transectionId;
		this.accountId = accountId;
		this.accountTypeID = accountTypeID;
		this.transactionAmount = transactionAmount;
		this.transactionDate = transactionDate;
		this.transactionStatus = transactionStatus;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	// Getters and setters method
	public int getTransectionId() {
		return transectionId;
	}

	public void setTransectionId(int transectionId) {
		this.transectionId = transectionId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getAccountTypeID() {
		return accountTypeID;
	}

	public void setAccountTypeID(int accountTypeID) {
		this.accountTypeID = accountTypeID;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public String recordTransactionDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date(); 
	    
	    return formatter.format(date);
	}

	// To string method
	@Override
	public String toString() {
		return "Transaction [transectionId=" + transectionId + ", accountId=" + accountId + ", accountTypeID="
				+ accountTypeID + ", transactionAmount=" + transactionAmount + ", transactionDate=" + transactionDate
				+ "]";
	}


	

}
