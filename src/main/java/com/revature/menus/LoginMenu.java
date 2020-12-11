package com.revature.menus;

import java.util.List;

import com.revature.launcher.MyApp;
import com.revature.models.MenuOption;
import com.revature.models.User;
import com.revature.repositories.MenuDAO;
import com.revature.services.UserService;

public class LoginMenu extends Menu {

	UserService userService;

	public LoginMenu(MenuDAO menu, List<MenuOption> options) {
		super(menu, options);
		// TODO Auto-generated constructor stub
	}

	public LoginMenu(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String display() {

		String display = "\n##########  Login PAGE  ##########\n";
		System.out.print(display);
		// this should be a number, but not necessarily
		try {
			System.out.print("Please enter your username:");
			String userName = this.userIn.nextLine();
			System.out.print("Please enter your password:");
			String password = this.userIn.nextLine();

			User user = this.userService.login(userName, password);
			if (user == null) {
				throw new Exception();
			}
			String jsonString = MyApp.mapper.writeValueAsString(user);
			return jsonString;
		} catch (Exception e) {
			System.out.println("\n\nCan not find the user, please re-enter!");
		}
		return "";
	}
}
