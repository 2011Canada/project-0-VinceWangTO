package com.revature.launcher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.util.ConnectionFactory;

public class TestConnection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConnectionFactory cf = ConnectionFactory.getConnectionFactory();

		Connection conn = cf.getConnection();

		try {
			String sql = "SELECT * FROM user_table;";

			System.out.println(sql);

			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet res = stmt.executeQuery();

			// System.out.println(res.getString("user_id"));
			while (res.next()) {
				// System.out.println("get Data");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
