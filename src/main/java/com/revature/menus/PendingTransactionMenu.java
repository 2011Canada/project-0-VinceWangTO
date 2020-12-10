package com.revature.menus;

import java.util.List;

import com.revature.models.Transaction;
import com.revature.models.User;
import com.revature.repositories.TransactionDAO;

public class PendingTransactionMenu extends JDBCMenu<Transaction> {

	TransactionDAO transaction;
	User user;

	public PendingTransactionMenu(User user, TransactionDAO transaction) {
		super();
		this.transaction = transaction;
		this.user = user;
	}

	@Override
	public String display() {
		// TODO Auto-generated method stub
		// from service get data to display
		List<Transaction> transactions = transaction.getPendingTransactionsByUserId(user.getUserId());
		String display = "";
		if (transactions == null) {
			display = "You don't have any transactions yet.";
			return display;
		}
		this.lines = transactions;
		for (int i = 0; i < transactions.size(); i++) {

			display += (i + 1) + " Pending transactions #: " + transactions.get(i).getTransactionId()
					+ ", pending balance $" + transactions.get(i).getAmount() + "\n";
		}
		return display;
	}
}
