package com.revature.menus;

import java.util.List;

import com.revature.models.Account;
import com.revature.services.EmployeeService;

public class EmployeeViewPengingAccountMenu extends JDBCMenu<Account> {

	EmployeeService employeeService;

	public EmployeeViewPengingAccountMenu(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	@Override
	public String display() {
		// TODO Auto-generated method stub
		// from service get data to display
		List<Account> accounts = employeeService.getPendingAccounts();

		String display = "##########  Pending Accounts  ##########\nPlease choose an option to process!\n";
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
