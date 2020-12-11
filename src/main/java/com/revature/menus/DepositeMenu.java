package com.revature.menus;

import java.math.BigDecimal;

import com.revature.models.Account;
import com.revature.services.CustomerService;

public class DepositeMenu extends JDBCMenu<Account> {
	CustomerService customerService;
	Account account;

	public DepositeMenu(Account account, CustomerService customerService) {
		super();
		this.account = account;
		this.customerService = customerService;
	}

	@Override
	public String display() {

		String display = "##########  Deposite Money  ##########\n";
		display += "Your account #" + this.account.getAccountId() + " have $" + this.account.getBalance()
				+ ".\nPlease enter deposite amount:";
		System.out.print(display);
		String input = this.userIn.nextLine();

		try {
			double amount = Double.parseDouble(input);
			if (amount < 0 || BigDecimal.valueOf(amount).scale() > 2) {
				System.out.println("\nPlease enter a valid deposite amount!\n");
				return "";
			}
			boolean success = customerService.deposit(this.account, amount);
			if (success) {
				return "SUCCESS";
			}
			return "";
		} catch (NumberFormatException e) {
			System.out.println("\nPlease enter a valid deposite amount!\n");
		} catch (Exception e) {

		}
		return "";
	}
}
