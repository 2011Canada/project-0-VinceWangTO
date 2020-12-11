package com.revature.models;

public class Transaction {

	private int transactionId;
	private int fromUserId;
	private int toAccount;
	private boolean transfered;

	private double amount;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Transaction() {
	}

	public Transaction(int transactionId, int fromUserId, int toAccount, double amount, boolean transfered) {
		super();
		this.transactionId = transactionId;
		this.fromUserId = fromUserId;
		this.toAccount = toAccount;
		this.transfered = transfered;
		this.amount = amount;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(int fromUserId) {
		this.fromUserId = fromUserId;
	}

	public int getToAccount() {
		return toAccount;
	}

	public void setToAccount(int toAccount) {
		this.toAccount = toAccount;
	}

	public boolean getTransfered() {
		return transfered;
	}

	public void setTransfered(boolean transfered) {
		this.transfered = transfered;
	}

}
