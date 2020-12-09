package com.revature.services;

import com.revature.models.Account;
import com.revature.models.Transaction;

public interface CustomerService {

	public Account applyNewAccount(double balance);

	public Account viewBalance(int accountId);

	public Account withdrawal(Account account, double amount);

	public Account deposit(Account account, double amount);

	public Transaction transferMoney(Account from, int toAccountId);

	public Transaction acceptMoney(Transaction pending);

}
