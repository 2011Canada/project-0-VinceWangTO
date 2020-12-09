package com.revature.launcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.menus.ApplyNewAccountMenu;
import com.revature.menus.CustomerMenu;
import com.revature.menus.LoginMenu;
import com.revature.menus.MainMenu;
import com.revature.menus.Menu;
import com.revature.menus.RegisterMenu;
import com.revature.menus.ViewAccountMenu;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.repositories.AccountDAO;
import com.revature.repositories.AccountDAOImpl;
import com.revature.repositories.MenuDAO;
import com.revature.repositories.MenuMemoryDAO;
import com.revature.services.CustomerService;
import com.revature.services.CustomerServiceImplementation;
import com.revature.services.UserService;
import com.revature.services.UserServiceImplementation;

public class BankLauncher {

	public static Logger logger = LogManager.getLogger("com.revature.project0");
	public static ObjectMapper mapper = new ObjectMapper();
	public static Scanner breaker = new Scanner(System.in);

	public static void goBack() {
		System.out.println("\nPress ENTER key to go back to customer page");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String s = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		// TODO Auto-generated method stub

		// logger.info("Server has started");

		while (true) {
			MenuDAO mainMenuDAO = new MenuMemoryDAO();
			Menu mainMenu = new MainMenu(mainMenuDAO);
			System.out.print(mainMenu.display());
			String mainOption = mainMenu.getUserOption();

			UserService userService = new UserServiceImplementation();
			AccountDAO accountDAO = new AccountDAOImpl();
			HomePage: {
				switch (mainOption) {
				case "login":
					while (true) {
						Menu loginMenu = new LoginMenu(userService);
						String userString = loginMenu.display();
						if (userString != "") {
							User loggedInUser = mapper.readValue(userString, User.class);

							while (true) {
								if (loggedInUser.getAccountType().equals("CUSTOMER")) {
									Menu customerMenu = new CustomerMenu(mainMenuDAO);
									System.out.println(customerMenu.display());
									String customerOption = customerMenu.getUserOption();

									CustomerService customerService = new CustomerServiceImplementation();

									CustomerPage: {
										boolean failed = true;
										switch (customerOption) {
										case "apply new account":
											do {
												Menu applyNewAccountMenu = new ApplyNewAccountMenu(customerService);
												String result = applyNewAccountMenu.display();

												if (!result.equals("")) {
													goBack();
													break CustomerPage;
												}
											} while (failed);
											break;
										case "view balance":
											do {
												ViewAccountMenu viewAccountMenu = new ViewAccountMenu(loggedInUser,
														accountDAO);
												System.out.println(viewAccountMenu.display());
												Account accountOption = viewAccountMenu.getUserOption();

												if (accountOption != null) {
													System.out.println("You account #: " + accountOption.getAccountId()
															+ " balance is : " + accountOption.getBalance());
													goBack();
													break CustomerPage;
												}

											} while (failed);

										case "withdrawal":
											break;
										case "deposite":
											break;
										case "transfer money":
											break;
										case "accept money":
											break;
										case "go back":
											break HomePage;
										default:

										}
									}
								} else {
									//
								}

							}
						}
						continue;

					}
				case "register":
					while (true) {
						Menu registerMenu = new RegisterMenu(userService);
						registerMenu.display();
					}
				default:

				}

			}
		}
	}
}
