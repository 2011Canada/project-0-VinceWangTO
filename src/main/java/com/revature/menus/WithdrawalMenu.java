package com.revature.menus;

import java.math.BigDecimal;

import com.revature.models.Account;
import com.revature.services.CustomerService;

public class WithdrawalMenu extends JDBCMenu<Account> {

	CustomerService customerService;
	Account account;

	public WithdrawalMenu(Account account, CustomerService customerService) {
		super();
		this.account = account;
		this.customerService = customerService;
	}

	@Override
	public String display() {

		String display = "##########  Withdrawal Money  ##########\n";
		display += "you have $" + this.account.getBalance() + " in you account.\nPlease enter withdrawal amount:";
		System.out.print(display);
		String input = this.userIn.nextLine();

		try {
			double amount = Double.parseDouble(input);
			if (amount < 0 || BigDecimal.valueOf(amount).scale() > 2) {
				System.out.println("\nPlease enter a valid withdrawal amount!\n");
				return "";
			}
			boolean success = customerService.withdrawal(this.account, amount);
			if (success) {
				return "SUCCESS";
			}
			return "";
		} catch (NumberFormatException e) {
			System.out.println("\nPlease enter a valid withdrawal amount!\n");
		} catch (Exception e) {

		}

		return "";
	}
}
