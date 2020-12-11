package com.revature.services;

import java.util.List;

import com.revature.models.Account;

public interface EmployeeService {

	public boolean manageNewAccount(Account account, String setStatue);

	public List<Account> viewCustomerAccount(String customerName);

	public void viewTransactions();

	public List<Account> getPendingAccounts();

}
