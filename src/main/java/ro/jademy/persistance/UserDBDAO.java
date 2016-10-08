package ro.jademy.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import ro.jademy.domain.entities.User;
import ro.jademy.domain.entities.UserType;

public class UserDBDAO implements UserDAO {

	//private Properties importFile;
	private Connection connection;
	private static UserDBDAO soleInstance = new UserDBDAO();

	private UserDBDAO() {
	}

	public static UserDBDAO getInstance() {
		return soleInstance;
	}

	public void createUser(User user) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("INSERt INTO USERS (USERNAME, PASSWORD, EMAIL, FIRST_NAME, LAST_NAME) VALUES (?,?,?,?,?)");
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmailAddress());
			statement.setString(4, user.getFirst_name());
			statement.setString(5, user.getLast_name());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Cannot connect to database", e);
		}

	}

	public User getUserByUsername(String username) {
		try {
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
			user.setFirst_name(result.getString("first_name"));
			user.setLast_name(result.getString("last_name"));
			return user;
		} catch (SQLException e) {
			throw new RuntimeException("Cannot connect to database", e);
		}
	}

	public void updateUser(User user) {
		try {
			PreparedStatement getStatement = connection.prepareStatement("SELECT * FROM USERS WHERE USERNAME = ?");
			getStatement.setString(1, user.getUsername());
			ResultSet rs = getStatement.executeQuery();
			if (!rs.next()) {
				return;
			}
			PreparedStatement putStatement = connection
					.prepareStatement("UPDATE USERS SET FIRST_NAME=?, LAST_NAME=?, EMAIL=?, PASSWORD=?, UUID=? WHERE USERNAME =?");
			putStatement.setString(1, user.getFirst_name());
			putStatement.setString(2, user.getLast_name());
			putStatement.setString(3, user.getEmailAddress());
			putStatement.setString(4, user.getPassword());
			putStatement.setString(5, user.getUuid());
			putStatement.setString(6, user.getUsername());
			putStatement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Cannot connect to database", e);
		}
	}

	
	public User getUserByUuid(String uuid) {
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM  USERS WHERE uuid = ?");
			statement.setString(1, uuid);
			ResultSet result = statement.executeQuery();
			if (!result.next()) {
				return null;
			}
			User user = new User();
			user.setUsername(result.getString("username"));
			user.setPassword(result.getString("password"));
			user.setEmailAddress(result.getString("email"));
			user.setFirst_name(result.getString("first_name"));
			user.setLast_name(result.getString("last_name"));
			user.setUuid(uuid);
			return user;
		} catch (SQLException e) {
			throw new RuntimeException("Cannot connect to database", e);
		}
	}

}
