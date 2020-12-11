package com.revature.menus;

import java.util.List;

import com.revature.models.Transaction;
import com.revature.models.User;
import com.revature.services.CustomerService;

public class PendingTransactionMenu extends JDBCMenu<Transaction> {

	User user;
	CustomerService customerService;

	public PendingTransactionMenu(User user, CustomerService customerService) {
		super();
		this.customerService = customerService;
		this.user = user;
	}

	@Override
	public String display() {

		List<Transaction> transactions = customerService.getPendingTransaction(user.getUserId());
		String display = "";
		if (transactions.size() == 0) {
			display = "You don't have any transactions yet.";
			return display;
		}
		this.lines = transactions;
		for (int i = 0; i < transactions.size(); i++) {

			display += (i + 1) + " Pending transactions #: " + transactions.get(i).getTransactionId()
					+ ", transfer into account #" + transactions.get(i).getToAccount() + ", pending balance $"
					+ transactions.get(i).getAmount() + "\n";
		}
		return display;
	}
}
