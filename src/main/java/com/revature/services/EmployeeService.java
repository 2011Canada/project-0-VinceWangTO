package com.revature.services;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.Transaction;

public interface EmployeeService {

	public boolean manageNewAccount(Account account, String setStatue);

	public List<Account> viewCustomerAccount(String customerName);

	public List<Transaction> viewTransactions();

	public List<Account> getPendingAccounts();

}
