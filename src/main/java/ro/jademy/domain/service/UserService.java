package ro.jademy.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import ro.jademy.domain.entities.User;
import ro.jademy.persistance.UserDAO;


@Service
public class UserService {

	public User checkPassword(User user) {
		User dbUser = getUserByUsername(user.getUsername());

		if (dbUser == null) {
			return dbUser;
		}
		if (dbUser.getPassword().equals(user.getPassword())) {
			return dbUser;
		}
		return null;
	}

	public User getUserByUsername(String username) {
		return UserDAO.getInstance().getUserByUsername(username);
	}

	public User saveUser(User user) {
		UserDAO.getInstance().createUser(user);
		return user;
	}
		
	public void updateUser(User user){
		UserDAO.getInstance().updateUser(user);
	}
	
	public User getUserByUuid(String uuid) {
		return UserDAO.getInstance().getUserByUuid(uuid);
	}

	

	public void updateUserPassword(User user, String password) {
		user.setPassword(password);
		updateUser(user);	
	}

	public void resetUuid(User user) {
		user.setUuid(UUID.randomUUID().toString());	
	}

	
	
	
}
