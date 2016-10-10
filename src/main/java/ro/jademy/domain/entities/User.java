package ro.jademy.domain.entities;

public class User {
	private String username;
	private String password;
	private String emailAddress;
	private String uuid;
	private UserType userType;
	private String firstName;
	private String lastName;

	// constructors
	public User() {
	}

	public User(String username, String password, String emailAddress) {
		this.username = username;
		this.password = password;
		this.emailAddress = emailAddress;
	}

	public User(String username, String password, String emailAddress, UserType userType, String first_name, String last_name) {
		this(username, password, emailAddress);
		this.userType = userType;
		this.firstName = first_name;
		this.lastName=last_name;
	}

	public User(String username, String password, String emailAddress, String uuid, UserType userType, String first_name, String last_name) {
		this(username, password, emailAddress, userType, first_name, last_name);
		this.uuid = uuid;
		this.firstName = first_name;
		this.lastName=last_name;
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

	public String getFirst_name() {
		return firstName;
	}

	public void setFirst_name(String first_name) {
		this.firstName = first_name;
	}

	public String getLast_name() {
		return lastName;
	}

	public void setLast_name(String last_name) {
		this.lastName = last_name;
	}
}
