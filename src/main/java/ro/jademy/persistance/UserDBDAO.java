package ro.jademy.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import ro.jademy.domain.entities.User;
import ro.jademy.domain.entities.UserType;

public class UserDBDAO {

	private Properties importFile;
	private static UserDBDAO soleInstance = new UserDBDAO();

	private UserDBDAO() {
	}

	public static UserDBDAO getInstance() {
		return soleInstance;
	}

	public User getUserByUsername(String username) {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM  USERS WHERE USERNAME = ?");
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			if (!result.next()) {
				return null;
			}
			User user = new User();
			user.setPassword(result.getString("password"));
			user.setEmailAddress(result.getString("email"));
			user.setUuid(result.getString("uuid"));
			user.setUsername(result.getString("username"));
			return user;
		} catch (SQLException e) {
			throw new RuntimeException("Cannot connect to database", e);
		}

	}

	public void createUser(User user) {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement("INSERt INTO USERS (USERNAME, PASSWORD, EMAIL) VALUES (?,?,?)");
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmailAddress());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Cannot connect to database", e);
		}

	}

	public void updateUser(User user) {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement getStatement = connection.prepareStatement(
					"SELECT * FROM USERS WHERE USERNAME = ?");
			getStatement.setString(1, user.getUsername());
			ResultSet rs = getStatement.executeQuery();
			if (!rs.next()) {
				return;
			}
			
			PreparedStatement putStatement = connection.prepareStatement(
					"UPDATE USERS SET EMAIL=?  WHERE USERNAME =?; "+
					"UPDATE USERS SET PASSWORD=?  WHERE USERNAME =?; "
					);
			putStatement.setString(1, user.getEmailAddress());
			putStatement.setString(2, user.getUsername());
			putStatement.setString(3, user.getPassword());
			putStatement.setString(4, user.getUsername());
			putStatement.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("Cannot connect to database", e);
		}

	}

	public User getUserByUuid(String uuid) {
		int i = 0;
		while (true) {
			i++;
			String dbUuid = importFile.getProperty("user[" + i + "].uuid");
			if (dbUuid == null) {
				return null;
			}
			if (dbUuid.equals(uuid)) {
				String dbUsername = importFile.getProperty("user[" + i + "].username");
				String dbPassword = importFile.getProperty("user[" + i + "].password");
				String dbEmailAddress = importFile.getProperty("user[" + i + "].emailAddress");
				UserType dbUserType = UserType.valueOf(importFile.getProperty("user[" + i + "].customer"));
				User user = new User(dbUsername, dbPassword, dbEmailAddress, uuid, dbUserType);
				return user;
			}
		}
	}

}
