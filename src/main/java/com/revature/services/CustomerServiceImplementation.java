package com.revature.services;

import com.revature.launcher.BankLauncher;
import com.revature.models.Account;
import com.revature.models.Transaction;

public class CustomerServiceImplementation implements CustomerService {

	@Override
	public Account applyNewAccount(double balance) {
		BankLauncher.logger.debug("A user try to login.");

		System.out.println("\nWaiting bank to approve the account, account starting balance: " + balance);

		Account newAccount = new Account();
		newAccount.setActive(false);
		newAccount.setUserId(123);
		return newAccount;
	}

	@Override
	public Account viewBalance(int accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account withdrawal(Account account, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account deposit(Account account, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction transferMoney(Account from, int toAccountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction acceptMoney(Transaction pending) {
		// TODO Auto-generated method stub
		return null;
	}

}
