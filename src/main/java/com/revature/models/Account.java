package com.revature.models;

public class Account {
	private int accountId;
	private double balance;
	private int userId;
	private boolean isActive;

	public Account() {
	}

	public Account(int accountId, double balance, int userId, boolean isActive) {
		this.accountId = accountId;
		this.balance = balance;
		this.userId = userId;
		this.isActive = isActive;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}