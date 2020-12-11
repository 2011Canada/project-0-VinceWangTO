package com.revature.menus;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.CustomerService;

public class ViewAccountMenu extends JDBCMenu<Account> {

	User user;
	CustomerService customerService;

	public ViewAccountMenu(User user, CustomerService customerService) {
		super();
		this.customerService = customerService;
		this.user = user;
	}

	@Override
	public String display() {

		List<Account> accounts = customerService.viewBalance(user.getUserId());
		String display = "";

		if (accounts.size() == 0) {
			display = "You don't have any accounts yet.";
			return display;
		}
		this.lines = accounts;
		for (int i = 0; i < accounts.size(); i++) {

			display += (i + 1) + " Account#: " + accounts.get(i).getAccountId() + "\n";
		}
		return display;
	}

}
