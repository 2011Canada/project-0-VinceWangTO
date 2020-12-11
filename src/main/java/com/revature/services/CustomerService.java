package com.revature.services;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.Transaction;

public interface CustomerService {

	public boolean applyNewAccount(int userId, double account);

	public List<Account> viewBalance(int accountId);

	public List<Transaction> getPendingTransaction(int userId);

	public boolean withdrawal(Account account, double amount);

	public boolean deposit(Account account, double amount);

	public boolean transferMoney(Account from, int toAccountId, double amount);

	public boolean acceptMoney(Transaction pending);

}
