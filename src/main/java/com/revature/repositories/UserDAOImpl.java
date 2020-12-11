package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.User;
import com.revature.util.ConnectionFactory;

public class UserDAOImpl implements UserDAO {

	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();
	PreparedStatement stmt = null;

	@Override
	public User findUserByName(String userName) {

		User user = null;
		Connection conn = cf.getConnection();
		try {
			conn.setAutoCommit(false);

			String sql = "SELECT * FROM user_table WHERE user_name = '" + userName + "';";

			stmt = conn.prepareStatement(sql);

			ResultSet res = stmt.executeQuery();

			if (res.next()) {
				user = new User();
				user.setUserId((res.getInt("User_Id")));
				user.setUsername(res.getString("User_name"));
				user.setPassword(res.getString("User_Password"));
				user.setAccountType(res.getString("Account_Type"));

			}
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

		return user;
	}

	@Override
	public boolean registerUser(User user) {
		Connection conn = cf.getConnection();
		try {
			conn.setAutoCommit(false);

			String sql = "INSERT INTO user_table (User_name, user_password, account_Type) VALUES (?, ?, ?);";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getAccountType());

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

}
