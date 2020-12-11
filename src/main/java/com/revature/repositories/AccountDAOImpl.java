package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.util.ConnectionFactory;

public class AccountDAOImpl implements AccountDAO {

	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();
	PreparedStatement stmt = null;
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

		List<Account> accounts = new ArrayList<Account>();
		Connection conn = cf.getConnection();
		try {
			conn.setAutoCommit(false);
			String sql = "SELECT * FROM account_table WHERE status = 'PENDING';";
			stmt = conn.prepareStatement(sql);
			ResultSet res = stmt.executeQuery();

			while (res.next()) {
				Account account = new Account();
				account.setAccountId(res.getInt("account_Id"));
				account.setUserId(res.getInt("user_Id"));
				account.setBalance(res.getDouble("balance"));
				account.setStatus(res.getString("status"));
				accounts.add(account);
			}
			pendingNumber = accounts.size();
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

		return accounts;
	}

	@Override
	public List<Account> getAccountsByUserId(int userId) {

		List<Account> accounts = new ArrayList<Account>();
		Connection conn = cf.getConnection();
		try {
			conn.setAutoCommit(false);
			String sql = "SELECT * FROM account_table WHERE user_Id = '" + userId + "'  AND status='ACTIVE';";
			stmt = conn.prepareStatement(sql);
			ResultSet res = stmt.executeQuery();

			while (res.next()) {
				Account account = new Account();
				account.setAccountId(res.getInt("account_Id"));
				account.setUserId(res.getInt("user_Id"));
				account.setBalance(res.getDouble("balance"));
				account.setStatus(res.getString("status"));
				accounts.add(account);
			}
			pendingNumber = accounts.size();
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
		Connection conn = cf.getConnection();
		try {
			conn.setAutoCommit(false);

			String sql = "UPDATE account_table SET status=? WHERE account_Id=? ;";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, account.getStatus());
			stmt.setInt(2, account.getAccountId());

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
	public List<Account> getAccountsByUsername(String username) {
		List<Account> accounts = new ArrayList<Account>();
		Connection conn = cf.getConnection();
		try {
			conn.setAutoCommit(false);

			String sql = "SELECT account_table.account_id, account_table.user_id as user_id , account_table.balance as balance, account_table.status as status FROM account_table inner join user_table on account_table.user_id = user_table.user_id"
					+ "  where user_table.user_name='" + username + "' AND status = 'ACTIVE';";

			// String sql = "SELECT * FROM account_table WHERE user_Id = '" + userId + "'
			// AND status='ACTIVE';";
			stmt = conn.prepareStatement(sql);
			ResultSet res = stmt.executeQuery();

			while (res.next()) {
				Account account = new Account();
				account.setAccountId(res.getInt("account_Id"));
				account.setUserId(res.getInt("user_Id"));
				account.setBalance(res.getDouble("balance"));
				account.setStatus(res.getString("status"));
				accounts.add(account);
			}
			pendingNumber = accounts.size();
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

		return accounts;
	}

}
