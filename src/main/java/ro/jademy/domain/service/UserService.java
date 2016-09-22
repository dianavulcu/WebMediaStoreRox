package ro.jademy.domain.service;

import ro.jademy.domain.entities.User;
import ro.jademy.persistance.UserDAO;

public class UserService {
//	public boolean checkPassword(User user) {
//		User dbUser = getUser(user.getUsername());
//
//		if (dbUser == null) {
//			return false;
//		}
//		if (dbUser.getPassword().equals(user.getPassword())) {
//			return true;
//		}
//		return false;
//	}

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
		UserDAO userDAO = UserDAO.getInstance();
		return userDAO.getUserByUsername(username);
	}

	public User saveUser(User user) {
		UserDAO.getInstance().createUser(user);
		return null;
	}
		
	public void updateUserService(User user){
		UserDAO userUpdated = UserDAO.getInstance();
		userUpdated.updateUser(user);
		
	}
	
	public User getUuidService(String uuid){
		UserDAO userByUuid = UserDAO.getInstance();
		return userByUuid.getUserByUuid(uuid);
		
	}
	
	
}
