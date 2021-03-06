package com.revature.services;

import java.text.DecimalFormat;
import java.util.List;

import com.revature.launcher.MyApp;
import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.repositories.AccountDAOImpl;
import com.revature.repositories.TransactionDAOlmpl;

public class CustomerServiceImplementation implements CustomerService {

	private AccountDAOImpl accountd;
	private TransactionDAOlmpl transd;

	public CustomerServiceImplementation(AccountDAOImpl accountd, TransactionDAOlmpl transd) {
		this.accountd = accountd;
		this.transd = transd;
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
	public List<Account> viewBalance(int accountId) {
		return accountd.getAccountsByUserId(accountId);
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
		if (this.accountd.updateAccount(account)) {
			System.out.print("From #: " + account.getAccountId());
			System.out.println(" withdrawal $" + amount + ", remaining balance: $" + account.getBalance());

			String message = "Customer #" + account.getUserId() + " withdrawal $" + amount + " from account #"
					+ account.getAccountId() + ".";
			MyApp.logger.info(message);
			return true;
		}
		return false;
	}

	@Override
	public boolean deposit(Account account, double amount) {

		double startBalance = account.getBalance();
		double endBalance = account.getBalance() + amount;
		account.setBalance(endBalance);

		if (this.accountd.updateAccount(account)) {
			System.out.println("\nAccount#" + account.getAccountId() + " starting balance: $" + startBalance
					+ ", deposit $" + amount + "\ntotal balance: $" + endBalance);

			String message = "Customer #" + account.getUserId() + " deposit $" + amount + " to account #"
					+ account.getAccountId() + ".";
			MyApp.logger.info(message);

			return true;
		}
		return false;

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

		from.setBalance(balance);

		Transaction transaction = new Transaction();
		transaction.setFromUserId(from.getUserId());
		transaction.setToAccount(toAccountId);
		transaction.setAmount(amount);
		transaction.setTransfered(false);

		if (this.accountd.updateAccount(from) && this.transd.addTransaction(transaction)) {
			System.out.println("From account #" + from.getAccountId() + " tranfers to account #" + toAccountId + " $"
					+ amount + ", you remain balance: $" + f.format(balance));

			String message = "Customer #" + from.getUserId() + " transfer $" + amount + " to account #" + toAccountId
					+ ".";
			MyApp.logger.info(message);

			return true;
		} else {
			System.out.println("Something went wrong, please wait and try again");
			return false;
		}

	}

	@Override
	public boolean acceptMoney(Transaction pending) {
		// TODO Auto-generated method stub

		pending.setTransfered(true);

		return this.transd.updateTransaction(pending)
				&& this.accountd.acceptMoney(pending.getToAccount(), pending.getAmount());

	}

	@Override
	public List<Transaction> getPendingTransaction(int userId) {
		return this.transd.getPendingTransactionsByUserId(userId);
	}

}
