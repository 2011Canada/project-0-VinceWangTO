package com.revature.repositories;

import java.util.List;

import com.revature.models.Transaction;

public interface TransactionDAO {

	public List<Transaction> getAllTransactions();

	public List<Transaction> getTransactionsByUserId(int userId);

	public List<Transaction> getPendingTransactionsByUserId(int userId);

	public boolean addTransaction(Transaction transaction);

	public boolean updateTransaction(Transaction transaction);

	public boolean deleteTransaction(Transaction transaction);
}
