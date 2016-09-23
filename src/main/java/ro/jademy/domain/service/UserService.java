package ro.jademy.domain.service;

import org.springframework.stereotype.Service;

import ro.jademy.domain.entities.User;
import ro.jademy.persistance.UserDAO;


@Service
public class UserService {

	public User checkPassword(User user) {
		User dbUser = getUser(user.getUsername());

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
		
	public void updateUserService(User user){
		UserDAO.getInstance().updateUser(user);
		
	}
	
	public User getUuidService(String uuid){
		return UserDAO.getInstance().getUserByUuid(uuid);
		
	}
	
	
}
