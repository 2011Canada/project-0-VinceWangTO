package com.revature.menus;

import java.math.BigDecimal;

import com.revature.launcher.BankLauncher;
import com.revature.models.Account;
import com.revature.services.CustomerService;

public class ApplyNewAccountMenu extends Menu {

	CustomerService customerService;

	public ApplyNewAccountMenu(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@Override
	public String display() {

		String display = "##########  APPLY NEW ACCOUNT ##########\n";
		System.out.print(display);

		System.out.print("Please enter your starting balance:");

		String input = this.userIn.nextLine();

		try {
			double startBalance = Double.parseDouble(input);
			if (startBalance < 0 || BigDecimal.valueOf(startBalance).scale() > 2) {
				System.out.println("\nPlease enter a valid start balance!\n");
				return "";
			}
			Account newAccount = customerService.applyNewAccount(startBalance);
			String jsonString = BankLauncher.mapper.writeValueAsString(newAccount);
			return jsonString;

		} catch (NumberFormatException e) {
			System.out.println("\nPlease enter a valid start balance!\n");
		} catch (Exception e) {

		}

		return "";
	}

}
