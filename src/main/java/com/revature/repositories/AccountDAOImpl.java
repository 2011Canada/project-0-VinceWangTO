package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.util.ConnectionFactory;

public class AccountDAOImpl implements AccountDAO {

	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();
	PreparedStatement stmt = null;

	static List<Account> pendingAccounts = new ArrayList<Account>();
	static boolean loading = false;
	static int pendingNumber = 0;

	public static int getPendingNumber() {
		return pendingNumber;
	}

	@Override
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAllPendingAccounts() {
		// TODO Auto-generated method stub
		if (pendingAccounts.size() == 0 && !loading) {
			pendingAccounts.add(new Account(10000, 20.2, 33));
			pendingAccounts.add(new Account(10040, 1120.2, 33));
			pendingAccounts.add(new Account(10020, 5520.2, 33));
			pendingAccounts.add(new Account(12000, 520.2, 33));
			loading = true;
			pendingNumber = pendingAccounts.size();
		}

		return pendingAccounts;
	}

	@Override
	public List<Account> getAccountsByUserId(int userId) {
		// TODO Auto-generated method stub
		List<Account> accounts = new ArrayList<Account>();

		// public Account(int accountId, double balance, int userId, boolean isActive) {
		accounts.add(new Account(10000, 20.2, 33));
		accounts.add(new Account(10040, 1120.2, 33));
		accounts.add(new Account(10020, 5520.2, 33));
		accounts.add(new Account(12000, 520.2, 33));

		return accounts;
	}

	@Override
	public boolean addAccount(Account account) {
		Connection conn = cf.getConnection();
		try {
			conn.setAutoCommit(false);

			String sql = "INSERT INTO account_table (user_id, balance, status) VALUES (?, ?, ?);";
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, account.getUserId());
			stmt.setDouble(2, account.getBalance());
			stmt.setString(3, account.getStatus());

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
	public boolean updateAccount(Account account) {
		return false;
	}

	@Override
	public boolean deleteAccount(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean manageNewAccount(Account account) {
		// TODO Auto-generated method stub
		pendingAccounts.remove(account);
		pendingNumber--;
		return true;
	}

}
