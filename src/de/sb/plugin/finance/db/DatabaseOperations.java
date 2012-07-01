package de.sb.plugin.finance.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.sb.plugin.finance.entities.Account;

public class DatabaseOperations {
	private static Connection connect = null;
	private static PreparedStatement preparedStatement = null;

	private static void close() {
		try {
			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

	public static void insertAccount(final Account account) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/finance_db?" + "user=root&password=admin");

			preparedStatement = connect.prepareStatement("insert into  finance_db.account values (default,?, ?, ?)");
			preparedStatement.setString(1, account.getName());
			preparedStatement.setString(2, account.getDescription());
			preparedStatement.setString(3, "todo");
			preparedStatement.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			// TODO Logger einfügen
		} finally {
			close();
		}

	}

}
