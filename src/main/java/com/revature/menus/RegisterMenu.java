package com.revature.menus;

import java.util.List;

import com.revature.models.MenuOption;
import com.revature.repositories.MenuDAO;
import com.revature.services.UserService;

public class RegisterMenu extends Menu {

	UserService userService;

	public RegisterMenu(MenuDAO menu, List<MenuOption> options) {
		super(menu, options);
		// TODO Auto-generated constructor stub
	}

	public RegisterMenu(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String display() {

		String display = "\n##########  Register PAGE  ##########\n";
		System.out.print(display);
		// this should be a number, but not necessarily
		try {
			System.out.print("Please enter a username:");
			String userName = this.userIn.nextLine();
			System.out.print("Please enter a password:");
			String password = this.userIn.nextLine();

			this.userService.register(userName, password);

			System.out.print("\n");
		} catch (Exception e) {
			System.out.println("Make A valid choice please");
		}
		return "";
	}

//	public void readUserData() {
//
//		String display = "\n##########  Register PAGE  ##########\n";
//
//		System.out.print("Please enter a username:");
//		String userName = this.userIn.nextLine();
//		System.out.print("Please enter a password:");
//		String password = this.userIn.nextLine();
//
//		System.out.print("\n");
//
//	}
}
