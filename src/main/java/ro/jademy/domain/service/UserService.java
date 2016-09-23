package ro.jademy.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import ro.jademy.domain.entities.User;
import ro.jademy.persistance.UserDAO;


@Service
public class UserService {

	public User checkPassword(User user) {
		User dbUser = getUserByUserName(user.getUsername());

		if (dbUser == null) {
			return dbUser;
		}
		if (dbUser.getPassword().equals(user.getPassword())) {
			return dbUser;
		}
		return null;
	}

	public User getUser(String username) {
		return UserDAO.getInstance().getUserByUsername(username);
	}

	public User saveUser(User user) {
		UserDAO.getInstance().createUser(user);
		return user;
	}
		
		
	}
	
	public void resetUuid(User user) {
		return UserDAO.getInstance().getUserByUuid(uuid);
		
	}

	
	
	
}
