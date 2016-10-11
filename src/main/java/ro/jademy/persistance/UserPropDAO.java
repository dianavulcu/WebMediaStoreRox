package ro.jademy.persistance;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.TreeSet;

import ro.jademy.domain.entities.ShoppingCart;
import ro.jademy.domain.entities.User;
import ro.jademy.domain.entities.UserType;

public class UserPropDAO implements UserDAO {

	private Properties importFile;
	private static UserPropDAO soleInstance = new UserPropDAO();

	private UserPropDAO() {
		importFile = new Properties() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public synchronized Enumeration<Object> keys() {
				return Collections.enumeration(new TreeSet<Object>(keySet()));
			}
		};
		try {
			FileInputStream stream = new FileInputStream("user.properties");
			importFile.load(stream);
		} catch (IOException e) {
			throw new RuntimeException("Cannot open user.properties", e);
		}
	}

	public static UserPropDAO getInstance() {
		return soleInstance;
	}

	public User getUserByUsername(String username) {
		int i = 0;
		while (true) {
			i++;
			String dbUsername = importFile.getProperty("user[" + i + "].username");
			if (dbUsername == null) {
				return null;
			}
			if (dbUsername.equals(username)) {
				String dbPassword = importFile.getProperty("user[" + i + "].password");
				String dbEmailAddress = importFile.getProperty("user[" + i + "].emailAddress");
				UserType dbUserType = UserType.valueOf(importFile.getProperty("user[" + i + "].customer"));
				String dbFirst_Name = importFile.getProperty("user[" + i + "].first_name");
				String dbLast_Name = importFile.getProperty("user[" + i + "].last_name");
				User dbUser = new User(dbUsername, dbPassword, dbEmailAddress, dbUserType, dbFirst_Name, dbLast_Name);
				return dbUser;
			}
		}
	}

	public int getNextUserIndex() {
		int i = 0;
		while (true) {
			i++;
			String dbUsername = importFile.getProperty("user[" + i + "].username");
			if (dbUsername == null) {
				return i;
			}
		}
	}

	public void createUser(User user) {
		int index = getNextUserIndex();
		importFile.setProperty("user[" + index + "].username", user.getUsername());
		importFile.setProperty("user[" + index + "].password", user.getPassword());
		importFile.setProperty("user[" + index + "].emailAddress", user.getEmailAddress());
		importFile.setProperty("user[" + index + "].customer", user.getUserType().name());
		importFile.setProperty("user[" + index + "].first_name", user.getFirst_name());
		importFile.setProperty("user[" + index + "].last_name", user.getLast_name());
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("user.properties");
			importFile.store(fos, new Date().toString());
			fos.close();
		} catch (IOException e) {
			throw new RuntimeException("Cannot save user.properties", e);
		}
	}

	public void updateUser(User user) {
		int i = 0;
		while (true) {
			i++;
			String dbUsername = importFile.getProperty("user[" + i + "].username");
			if (dbUsername == null) {
				return;
			}
			if (dbUsername.equals(user.getUsername())) {
				importFile.setProperty("user[" + i + "].password", user.getPassword());
				importFile.setProperty("user[" + i + "].emailAddress", user.getEmailAddress());
				importFile.setProperty("user[" + i + "].uuid", user.getUuid());
				importFile.setProperty("user[" + i + "].customer", user.getUserType().name());
				importFile.setProperty("user[" + i + "].first_name", user.getFirst_name());
				importFile.setProperty("user[" + i + "].last_name", user.getLast_name());
				FileOutputStream fos;
				try {
					fos = new FileOutputStream("user.properties");
					importFile.store(fos, new Date().toString());
					fos.close();
				} catch (IOException e) {
					throw new RuntimeException("Cannot save user.properties", e);
				}
			}
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
				String dbFirst_name = importFile.getProperty("user[" + i + "].first_name");
				String dbLast_name = importFile.getProperty("user[" + i + "].last_name");
				User user = new User(dbUsername, dbPassword, dbEmailAddress, dbUuid, dbUserType, dbFirst_name, dbLast_name);
				return user;
			}
		}
	}

	@Override
	public List<ShoppingCart> getShoppingCartsByUser(User currentUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByRememberMeId(String value) {
		// TODO Auto-generated method stub
		return null;
	}

}
