package com.revature.menus;

import java.util.List;

import com.revature.models.Account;
import com.revature.repositories.AccountDAO;

public class EmployeeViewPengingAccountMenu extends JDBCMenu<Account> {

	AccountDAO account;

	public EmployeeViewPengingAccountMenu(AccountDAO account) {
		super();
		this.account = account;
	}

	@Override
	public String display() {
		// TODO Auto-generated method stub
		// from service get data to display
		List<Account> accounts = account.getAllPendingAccounts();
		String display = "";
		if (accounts == null) {
			display = "There is no pending accounts.";
			return display;
		}
		this.lines = accounts;
		for (int i = 0; i < accounts.size(); i++) {

			display += (i + 1) + " Account#: " + accounts.get(i).getAccountId() + "\n";
		}
		return display;
	}

}
