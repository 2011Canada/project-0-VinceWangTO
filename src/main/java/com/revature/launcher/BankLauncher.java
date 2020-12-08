package com.revature.launcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.menus.CustomerMenu;
import com.revature.menus.MainMenu;
import com.revature.menus.Menu;
import com.revature.menus.RegisterMenu;
import com.revature.repositories.MenuDAO;
import com.revature.repositories.MenuMemoryDAO;
import com.revature.services.UserService;
import com.revature.services.UserServiceImplementation;

public class BankLauncher {

	public static Logger logger = LogManager.getLogger("com.revature.project0");

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		logger.info("Server has started");

		while (true) {
			MenuDAO mainMenuDAO = new MenuMemoryDAO();
			Menu mainMenu = new MainMenu(mainMenuDAO);
			System.out.print(mainMenu.display());
			String mainOption = mainMenu.getUserOption();

			switch (mainOption) {
			case "login":
				while (true) {
					Menu customerMenu = new CustomerMenu(mainMenuDAO);
					System.out.println(customerMenu.display());
					String customerOption = customerMenu.getUserOption();
					System.out.println(customerOption);

				}
			case "register":
				while (true) {
					UserService userService = new UserServiceImplementation();
					Menu registerMenu = new RegisterMenu(userService);
					registerMenu.display();
				}
			default:

			}

		}
	}

}
