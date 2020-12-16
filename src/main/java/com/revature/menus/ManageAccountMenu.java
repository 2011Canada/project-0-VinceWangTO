package com.revature.menus;

import com.revature.launcher.MyApp;
import com.revature.models.Account;
import com.revature.services.EmployeeService;

public class ManageAccountMenu extends JDBCMenu<Account> {

	EmployeeService employeeService;
	Account account;

	public ManageAccountMenu(Account account, EmployeeService employeeService) {
		super();
		this.account = account;
		this.employeeService = employeeService;
	}

	@Override
	public String display() {

		String display = "##########  Manage Account  ##########\n";

		System.out.print(display);

		try {
			System.out.println(
					"Pending Account #" + account.getAccountId() + ", start balance: $" + account.getBalance());
			System.out.println("Enter y to approve account/ Enter n to reject account:");

			String res = userIn.nextLine();

			if (!res.equalsIgnoreCase("y") && !res.equalsIgnoreCase("n")) {
				System.out.println("Enter y to approve account/ Enter n to reject account:");
				return "";
			}

			String status = res.equalsIgnoreCase("y") ? "ACTIVE" : "REJECTED";

			boolean success = employeeService.manageNewAccount(account, status);
			if (success) {

				if (status == "ACTIVE") {
					String message = "Customer #" + account.getUserId() + " creates a new account #"
							+ account.getAccountId() + ", with starting balance: $" + account.getBalance() + ".";
					MyApp.logger.info(message);
				}

				return "Account get " + status + "! ";
			}
			return "";
		} catch (Exception e) {

		}

		return "";
	}
}
