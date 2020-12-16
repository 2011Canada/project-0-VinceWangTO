package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Transaction;
import com.revature.util.ConnectionFactory;

public class TransactionDAOlmpl implements TransactionDAO {

	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();
	PreparedStatement stmt = null;

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

		List<Transaction> transactions = new ArrayList<Transaction>();
		Connection conn = cf.getConnection();
		try {
			conn.setAutoCommit(false);
			String sql = "SELECT * FROM account_table WHERE status = 'PENDING';";

			sql = "select transaction_table.transaction_id ,transaction_table.user_id , "
					+ "transaction_table.account_id , transaction_table.transfered ,transaction_table.amount, account_table.account_id "
					+ "from  transaction_table" + " inner join account_table "
					+ "on transaction_table.account_id  = account_table.account_id "
					+ "inner join user_table on user_table.user_id =  account_table.user_id "
					+ "where transaction_table.transfered = false and user_table.user_id = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);
			ResultSet res = stmt.executeQuery();

			while (res.next()) {
				Transaction transaction = new Transaction();
				transaction.setTransactionId(res.getInt("transaction_id"));
				transaction.setFromUserId(res.getInt("user_id"));
				transaction.setToAccount(res.getInt("account_id"));
				transaction.setTransfered(res.getBoolean("transfered"));
				transaction.setAmount(res.getDouble("amount"));
				transactions.add(transaction);
			}
			pendingNumber = transactions.size();

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.commit();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			cf.releaseConnection(conn);
		}

		return transactions;

	}

	@Override
	public boolean addTransaction(Transaction transaction) {
		Connection conn = cf.getConnection();
		try {
			conn.setAutoCommit(false);

			String sql = "INSERT INTO transaction_table (user_id,  account_Id, transfered, amount) VALUES (?, ?, ?,?);";

			// System.out.println(transaction.getFromUserId());
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, transaction.getFromUserId());
			stmt.setInt(2, transaction.getToAccount());
			stmt.setBoolean(3, transaction.getTransfered());
			stmt.setDouble(4, transaction.getAmount());

			// System.out.println(sql);

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

		Connection conn = cf.getConnection();
		try {
			conn.setAutoCommit(false);

			String sql = "UPDATE transaction_table SET user_id=?, account_id=?, amount=?, transfered=? WHERE transaction_Id=? ;";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, transaction.getFromUserId());
			stmt.setInt(2, transaction.getToAccount());
			stmt.setDouble(3, transaction.getAmount());
			stmt.setBoolean(4, transaction.getTransfered());
			stmt.setInt(5, transaction.getTransactionId());

			if (stmt.executeUpdate() != 0) {
				pendingNumber--;
				return true;
			} else
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
	public boolean deleteTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		return false;
	}

}
