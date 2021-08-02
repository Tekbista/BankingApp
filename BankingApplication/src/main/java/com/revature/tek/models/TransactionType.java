package com.revature.tek.models;

public class TransactionType {

	// Private Fields
	private int transectionTypeId;
	private String transectionType;

	// Default Constructor
	public TransactionType() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Parameterized Constructor
	public TransactionType( String transectionType) {
		super();
		this.transectionType = transectionType;
	}

	// Getters and setters
	public int getTransectionTypeId() {
		return transectionTypeId;
	}

	public void setTransectionTypeId(int transectionTypeId) {
		this.transectionTypeId = transectionTypeId;
	}

	public String getTransectionType() {
		return transectionType;
	}

	public void setTransectionType(String transectionType) {
		this.transectionType = transectionType;
	}

	// To string method
	@Override
	public String toString() {
		return "TransectionType [transectionTypeId=" + transectionTypeId + ", transectionType=" + transectionType + "]";
	}

}
