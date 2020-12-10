package com.revature.menus;

import java.math.BigDecimal;

import com.revature.models.Account;
import com.revature.services.CustomerService;

public class TransferMoneyMenu extends JDBCMenu<Account> {
	CustomerService customerService;
	Account account;

	public TransferMoneyMenu(Account account, CustomerService customerService) {
		super();
		this.account = account;
		this.customerService = customerService;
	}

	@Override
	public String display() {

		String display = "##########  Transfer Money  ##########\n";
		display += "you have $" + this.account.getBalance() + " in you account.";
		String input = "";
		int accountNumber = 0;
		System.out.print(display);

		try {
			System.out.println("Please enter transfer to account number:");
			accountNumber = Integer.parseInt(userIn.nextLine());
			System.out.println("Please enter transfer amount:");
			input = userIn.nextLine();
			double amount = Double.parseDouble(input);
			if (accountNumber < 0 || amount < 0 || BigDecimal.valueOf(amount).scale() > 2) {
				System.out.println("\nPlease enter a valid transfer amount!\n");
				return "";
			}
			boolean success = customerService.transferMoney(account, accountNumber, amount);
			if (success) {
				return "SUCCESS";
			}
			return "";
		} catch (NumberFormatException e) {
			System.out.println("\nPlease enter a valid transfer amount!\n");
		} catch (Exception e) {

		}

		return "";
	}
}
