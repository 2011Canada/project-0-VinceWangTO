package com.revature.services;

import com.revature.models.Account;
import com.revature.models.Transaction;

public interface CustomerService {

	public boolean applyNewAccount(int userId, double account);

	public Account viewBalance(int accountId);

	public boolean withdrawal(Account account, double amount);

	public boolean deposit(Account account, double amount);

	public boolean transferMoney(Account from, int toAccountId, double amount);

	public boolean acceptMoney(Transaction pending);

}
