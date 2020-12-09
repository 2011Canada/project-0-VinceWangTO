package com.revature.models;

public class Transaction {

	private int transactionId;
	private int fromAccount;
	private int toAccount;
	private boolean transfered;

	public int getTransferId() {
		return transactionId;
	}

	public void setTransferId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(int fromAccount) {
		this.fromAccount = fromAccount;
	}

	public int getToAccount() {
		return toAccount;
	}

	public void setToAccount(int toAccount) {
		this.toAccount = toAccount;
	}

	public boolean isTransfered() {
		return transfered;
	}

	public void setTransfered(boolean transfered) {
		this.transfered = transfered;
	}

}
