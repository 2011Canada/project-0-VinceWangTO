package com.revature.services;

import java.text.DecimalFormat;

import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.repositories.AccountDAOImpl;

public class CustomerServiceImplementation implements CustomerService {

	private AccountDAOImpl accountd;

	public CustomerServiceImplementation(AccountDAOImpl accountd) {
		this.accountd = accountd;
	}

	@Override
	public boolean applyNewAccount(int userId, double balance) {

		System.out.println("\nWaiting bank to approve the account, account starting balance: " + balance);
		Account newAccount = new Account();
		newAccount.setUserId(userId);
		newAccount.setBalance(balance);
		newAccount.setStatus("PENDING");
		return this.accountd.addAccount(newAccount);

	}

	@Override
	public Account viewBalance(int accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean withdrawal(Account account, double amount) {
		// TODO Auto-generated method stub
		if (account.getBalance() < amount) {
			System.out
					.println("\nYou balance is: $" + account.getBalance() + ", you can't withdrawal $" + amount + "\n");
			return false;
		}
		account.setBalance(account.getBalance() - amount);
		System.out.print("From #: " + account.getAccountId());
		System.out.println(" withdrawal $" + amount);
		return true;
	}

	@Override
	public boolean deposit(Account account, double amount) {
		// TODO Auto-generated method stub
		System.out.println("\nYou balance is: $" + account.getBalance() + ", you deposit $" + amount + "\n");
		System.out.println("You total balance is: $" + (account.getBalance() + amount));
		return true;

	}

	@Override
	public boolean transferMoney(Account from, int toAccountId, double amount) {
		// TODO Auto-generated method stub
		if (from.getBalance() < amount) {
			System.out.println("\nYou balance is: $" + from.getBalance() + ", you can't transfer $" + amount + "\n");
			return false;
		}

		double balance = from.getBalance() - amount;
		DecimalFormat f = new DecimalFormat("##.00");

		System.out.println("From account #" + from.getAccountId() + " tranfers to account #" + toAccountId + " $"
				+ amount + ", you remain balance: $" + f.format(balance));
		return true;
	}

	@Override
	public boolean acceptMoney(Transaction pending) {
		// TODO Auto-generated method stub
		return true;
	}

}
