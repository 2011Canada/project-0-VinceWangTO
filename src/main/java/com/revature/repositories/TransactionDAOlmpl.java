package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Transaction;

public class TransactionDAOlmpl implements TransactionDAO {

	static List<Transaction> transactions = new ArrayList<Transaction>();
	static boolean loading = false;
	static int pendingNumber = 0;

	public static int getPendingNumber() {
		return pendingNumber;
	}

	@Override
	public List<Transaction> getAllTransactions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getTransactionsByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getPendingTransactionsByUserId(int userId) {
		// TODO Auto-generated method stub

		// public Transaction(int transactionId, int fromAccount, int toAccount, double
		// amount, boolean transfered)
		if (transactions.isEmpty() && !loading) {
			transactions.add(new Transaction(20000, 1000, 10040, 10.22, false));
			transactions.add(new Transaction(20040, 1001, 10040, 333.1, false));
			transactions.add(new Transaction(20020, 1002, 12000, 303, false));
			transactions.add(new Transaction(22000, 1003, 12000, 3, false));
			loading = true;
			pendingNumber = transactions.size();
		}

		return transactions;

	}

	@Override
	public boolean addTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		// Update transaction status and account balance
		// int index = transactions.indexOf(transaction);
		transactions.remove(transaction);
		pendingNumber--;
		return true;
	}

	@Override
	public boolean deleteTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		return false;
	}

}
