package com.revature.menus;

import java.util.List;

import com.revature.models.Account;
import com.revature.services.EmployeeService;

public class EmployeeViewCustomerAccountMenu extends JDBCMenu<Account> {

	EmployeeService employeeService;

	public EmployeeViewCustomerAccountMenu(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	@Override
	public String display() {

		System.out.print("##########  View Customer Account  ##########\nPlease enter an account holder name: ");
		String display = "Please choose an account:\n";
		String input = this.userIn.nextLine();
		try {
			// int choice = Integer.parseInt(input);

			List<Account> accounts = employeeService.viewCustomerAccount(input);

			if (accounts.size() == 0) {
				display = "Customer don't have any accounts";
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
