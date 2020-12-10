package com.revature.models;

public class Account {
	private int accountId;
	private double balance;
	private int userId;
	private String status;

	public Account() {
	}

	public Account(int accountId, double balance, int userId) {
		this.accountId = accountId;
		this.balance = balance;
		this.userId = userId;
		this.status = "PENDING";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
