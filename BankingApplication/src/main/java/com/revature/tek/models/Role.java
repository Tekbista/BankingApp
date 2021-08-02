package com.revature.tek.models;

public class Role {

	// Private fields
	private int roleId;
	private String role;
	
	// Default Constructor
	public Role() {
		super();
	}

	// Parameterized Constructor
	public Role(int roleId, String role) {
		super();
		this.roleId = roleId;
		this.role = role;
	}

	// Getters and Setters
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	// to string method
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", role=" + role + "]";
	}
	
	
}
