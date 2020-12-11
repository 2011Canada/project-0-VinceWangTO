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
import com.revature.menus.DepositeMenu;
import com.revature.menus.EmployeeMenu;
import com.revature.menus.EmployeeViewCustomerAccountMenu;
import com.revature.menus.EmployeeViewPengingAccountMenu;
import com.revature.menus.LoginMenu;
import com.revature.menus.MainMenu;
import com.revature.menus.ManageAccountMenu;
import com.revature.menus.Menu;
import com.revature.menus.PendingTransactionMenu;
import com.revature.menus.RegisterMenu;
import com.revature.menus.TransferMoneyMenu;
import com.revature.menus.ViewAccountMenu;
import com.revature.menus.WithdrawalMenu;
import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.models.User;
import com.revature.repositories.AccountDAOImpl;
import com.revature.repositories.MenuDAO;
import com.revature.repositories.MenuMemoryDAO;
import com.revature.repositories.TransactionDAOlmpl;
import com.revature.repositories.UserDAOImpl;
import com.revature.services.CustomerService;
import com.revature.services.CustomerServiceImplementation;
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceImplementation;
import com.revature.services.UserService;
import com.revature.services.UserServiceImplementation;

public class BankLauncher {

	public static Logger logger = LogManager.getLogger("com.revature.project0");
	public static ObjectMapper mapper = new ObjectMapper();
	public static Scanner breaker = new Scanner(System.in);

	public static void goBack(String label) {
		System.out.println(label);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Account getAccount(User loggedInUser, CustomerService customerService) {
		ViewAccountMenu viewAccountMenu = new ViewAccountMenu(loggedInUser, customerService);
		System.out.println(viewAccountMenu.display());
		return viewAccountMenu.getUserOption();
	}

//	public static Account viewAccount(AccountDAO accountDAO) {
//		EmployeeViewAccountMenu employeeViewAccountMenu = new EmployeeViewAccountMenu(accountDAO);
//		System.out.println(employeeViewAccountMenu.display());
//		return employeeViewAccountMenu.getUserOption();
//	}

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		// TODO Auto-generated method stub

		// logger.info("Server has started");

		while (true) {
			MenuDAO mainMenuDAO = new MenuMemoryDAO();
			Menu mainMenu = new MainMenu(mainMenuDAO);
			System.out.print(mainMenu.display());
			String mainOption = mainMenu.getUserOption();

			// userService
			UserDAOImpl userd = new UserDAOImpl();
			UserService userService = new UserServiceImplementation(userd);

			AccountDAOImpl accountDAO = new AccountDAOImpl();
			TransactionDAOlmpl transactionDAO = new TransactionDAOlmpl();

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

									CustomerService customerService = new CustomerServiceImplementation(accountDAO);

									CustomerPage: {
										switch (customerOption) {
										case "apply new account":
											while (true) {
												Menu applyNewAccountMenu = new ApplyNewAccountMenu(customerService,
														loggedInUser);
												String result = applyNewAccountMenu.display();

												if (result == "SUCCESS") {
													goBack("\nPress ENTER key to go back to customer page");
													break CustomerPage;
												}

											}

										case "view balance":
											while (true) {
												Account accountOption = getAccount(loggedInUser, customerService);

												if (accountOption != null) {
													System.out.println("You account #: " + accountOption.getAccountId()
															+ " balance is : " + accountOption.getBalance());
													goBack("\nPress ENTER key to go back to customer page");
												}
												break CustomerPage;
											}

										case "withdrawal":
											while (true) {
												Account accountOption = getAccount(loggedInUser, customerService);
												if (accountOption != null) {
													WithdrawalPage: {
														while (true) {
															WithdrawalMenu withdrawalMenu = new WithdrawalMenu(
																	accountOption, customerService);
															String rs = withdrawalMenu.display();
															if (rs == "SUCCESS") {
																goBack("\nPress ENTER key to go back to customer page");
																break CustomerPage;
															} else {
																goBack("\nPress ENTER key to go back to account page");
																break WithdrawalPage;
															}
														}
													}
												}
											}
										case "deposite":
											while (true) {
												Account accountOption = getAccount(loggedInUser, customerService);
												if (accountOption != null) {
													while (true) {
														DepositeMenu depositeMenuMenu = new DepositeMenu(accountOption,
																customerService);
														String rs = depositeMenuMenu.display();
														if (rs == "SUCCESS") {
															goBack("\nPress ENTER key to go back to customer page");
															break CustomerPage;
														}
													}
												}
											}
										case "transfer money":
											while (true) {
												Account accountOption = getAccount(loggedInUser, customerService);
												if (accountOption != null) {
													TransferPage: {
														while (true) {

															TransferMoneyMenu transferMenuMenu = new TransferMoneyMenu(
																	accountOption, customerService);
															String rs = transferMenuMenu.display();
															if (rs == "SUCCESS") {
																goBack("\nPress ENTER key to go back to customer page");
																break CustomerPage;
															} else {
																goBack("\nPress ENTER key to go back to Transfer page");
																break TransferPage;
															}
														}
													}
												}
											}
										case "accept money":
											AcceptMoneyPage: {
												while (true) {
													PendingTransactionMenu pendingTransactionMenu = new PendingTransactionMenu(
															loggedInUser, transactionDAO);
													System.out.println(pendingTransactionMenu.display());
													Transaction transactionOption = pendingTransactionMenu
															.getUserOption();

													if (TransactionDAOlmpl.getPendingNumber() == 0) {
														System.out.println("You don't have pending transations!");
														goBack("\nPress ENTER key to go back to customer page");
														break CustomerPage;
													}
													if (transactionOption != null) {
														boolean result = transactionDAO
																.updateTransaction(transactionOption);
														if (!result) {
															System.out.println("Transaction failed!");
															break AcceptMoneyPage;
														}

													}
												}
											}
										case "go back":
											break HomePage;
										default:

										}
									}
								} else {
									// HERE is EMPLOYEE MENU
									Menu employeeMenu = new EmployeeMenu(mainMenuDAO);
									System.out.println(employeeMenu.display());
									String employeeOption = employeeMenu.getUserOption();
									EmployeeService employeeService = new EmployeeServiceImplementation(accountDAO);

									EmployeePage: {
										switch (employeeOption) {
										case "manage new accounts":
											while (true) {
												EmployeeViewPengingAccountMenu employeeViewAccountMenu = new EmployeeViewPengingAccountMenu(
														employeeService);
												System.out.println(employeeViewAccountMenu.display());

												Account accountOption = employeeViewAccountMenu.getUserOption();

												// System.out.println(accountOption);

												if (AccountDAOImpl.getPendingNumber() == 0) {
													System.out.println("You don't have pending transations!");
													goBack("\nPress ENTER key to go back to Employee page");
													break EmployeePage;
												}
												if (accountOption != null) {

													ManageAccountMenu manageAccountMenu = new ManageAccountMenu(
															accountOption, employeeService);
													String rs = manageAccountMenu.display();
													System.out.println(rs);
												}
											}
										case "view customer accounts":
											while (true) {
												EmployeeViewCustomerAccountMenu employeeViewCustomerAccountMenu = new EmployeeViewCustomerAccountMenu(
														employeeService);
												System.out.print(employeeViewCustomerAccountMenu.display());
												Account accountOption = employeeViewCustomerAccountMenu.getUserOption();

												if (accountOption != null) {
													System.out.println("Account #: " + accountOption.getAccountId()
															+ " balance is : " + accountOption.getBalance());
													goBack("\nPress ENTER key to go back to Employee page");

												}
												break EmployeePage;
											}
										case "view all transactions":
											break;
										case "go back":
											break HomePage;
										default:
										}

									}

								}
							}

						}
					}
				case "register":
					while (true) {
						Menu registerMenu = new RegisterMenu(userService);
						String rs = registerMenu.display();
						if (rs == "SUCCESS") {
							goBack("\nRegister is successful, press ENTER key to go back to Home page");
							break HomePage;
						}
					}
				default:

				}

			}
		}
	}
}
