package com.revature.menus;

import java.util.List;

import com.revature.models.Account;
import com.revature.repositories.AccountDAO;

public class EmployeeViewCustomerAccountMenu extends JDBCMenu<Account> {

	AccountDAO account;

	public EmployeeViewCustomerAccountMenu(AccountDAO account) {
		super();
		this.account = account;
	}

	@Override
	public String display() {

		System.out.print("##########  View Customer Account  ##########\nPlease enter a customer number: ");
		String display = "Please choose an account:\n";
		String input = this.userIn.nextLine();
		try {
			int choice = Integer.parseInt(input);

			List<Account> accounts = account.getAccountsByUserId(choice);

			if (accounts == null) {
				display = "User don't have any accounts";
				return display;
			}
			this.lines = accounts;
			for (int i = 0; i < accounts.size(); i++) {

				display += (i + 1) + " Account#: " + accounts.get(i).getAccountId() + "\n";
			}
			return display;
		} catch (NumberFormatException e) {
			System.out.println("Make A valid choice please!\n");
		}
		return "";
	}
}
