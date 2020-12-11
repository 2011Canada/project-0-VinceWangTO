package com.revature.menus;

import java.math.BigDecimal;

import com.revature.models.User;
import com.revature.services.CustomerService;

public class ApplyNewAccountMenu extends Menu {

	CustomerService customerService;
	User user;

	public ApplyNewAccountMenu(CustomerService customerService, User user) {
		super();
		this.customerService = customerService;
		this.user = user;
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
			if (customerService.applyNewAccount(this.user.getUserId(), startBalance))
				return "SUCCESS";
			else
				return "Failed to Register!";

		} catch (NumberFormatException e) {
			System.out.println("\nPlease enter a valid start balance!\n");
		} catch (Exception e) {

		}
		return "";
	}

}
