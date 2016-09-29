package ro.jademy.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	public static Connection getConnection() {
		try {
			Connection connection = DriverManager.getConnection(
					"jdbc:postgresql://ec2-54-243-203-141.compute-1.amazonaws.com:5432/db097ee5t7rs7r?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory",
					"xvseorxagpkwwn", "PTA8mq2JR2mBe1eR4IY6AhInua");
			return connection;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}