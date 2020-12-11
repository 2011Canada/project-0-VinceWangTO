package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Transaction;
import com.revature.util.ConnectionFactory;

public class TransactionDAOlmpl implements TransactionDAO {

	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();
	PreparedStatement stmt = null;

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
		Connection conn = cf.getConnection();
		try {
			conn.setAutoCommit(false);

			String sql = "INSERT INTO transaction_table (user_id,  account_Id, transfered, amount) VALUES (?, ?, ?,?);";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, transaction.getFromUserId());
			stmt.setInt(2, transaction.getToAccount());
			stmt.setBoolean(3, transaction.getTransfered());
			stmt.setDouble(4, transaction.getAmount());

			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		} finally {
			try {
				conn.commit();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			cf.releaseConnection(conn);
		}
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
