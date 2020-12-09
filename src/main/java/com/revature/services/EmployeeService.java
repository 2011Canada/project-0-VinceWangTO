package com.revature.services;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.Transaction;

public interface EmployeeService {

	public boolean manageNewAccount(Account account, boolean setStatue);

	public List<Account> viewCustomerAccount(int customerId);

	public List<Transaction> viewTransactions();

}
