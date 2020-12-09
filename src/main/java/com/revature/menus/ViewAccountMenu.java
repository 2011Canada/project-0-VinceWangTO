package com.revature.menus;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.repositories.AccountDAO;

public class ViewAccountMenu extends JDBCMenu<Account> {

	AccountDAO account;
	User user;

	public ViewAccountMenu(User user, AccountDAO account) {
		super();
		this.account = account;
		this.user = user;
	}

	@Override
	public String display() {
		// TODO Auto-generated method stub
		// from service get data to display
		List<Account> accounts = account.getAccountsByUserId(user.getUserId());
		String display = "";
		if (accounts == null) {
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
