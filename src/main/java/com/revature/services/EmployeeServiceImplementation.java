package com.revature.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.repositories.AccountDAO;

public class EmployeeServiceImplementation implements EmployeeService {

	AccountDAO accountDAO;

	public EmployeeServiceImplementation(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	@Override
	public boolean manageNewAccount(Account account, String setStatue) {
		// TODO Auto-generated method stub
		account.setStatus(setStatue);
		return accountDAO.manageNewAccount(account);
	}

	@Override
	public List<Account> viewCustomerAccount(String customerName) {
		return this.accountDAO.getAccountsByUsername(customerName);
	}

	@Override
	public void viewTransactions() {

		try (Scanner fileIn = new Scanner(new File("logs/trace.log"))) {
			while (fileIn.hasNextLine()) {
				System.out.println(fileIn.nextLine());
			}
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	}

	@Override
	public List<Account> getPendingAccounts() {
		return accountDAO.getAllPendingAccounts();
	}

}
