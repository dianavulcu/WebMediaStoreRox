package ro.jademy.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	public static Connection getConnection() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/media_store", "root",
					"root");
			return connection;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}