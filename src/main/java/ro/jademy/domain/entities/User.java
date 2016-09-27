package ro.jademy.domain.entities;

import ro.jademy.persistance.UserPropDAO;

public class User {
	private String username;
	private String password;
	private String emailAddress;
	private String uuid;
	private UserType userType;

	// constructors
	public User() {
	}

	public User(String username, String password, String emailAddress) {
		this.username = username;
		this.password = password;
		this.emailAddress = emailAddress;
	}

	public User(String username, String password, String emailAddress, UserType userType) {
		this(username, password, emailAddress);
		this.userType = userType;
	}

	public User(String username, String password, String emailAddress, String uuid, UserType userType) {
		this(username, password, emailAddress, userType);
		this.uuid = uuid;
	}

	// getters and setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
}
