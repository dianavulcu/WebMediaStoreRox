package ro.jademy.domain.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.jademy.domain.entities.ShoppingCart;
import ro.jademy.domain.entities.User;



@Service
public class UserService {
	
	@Autowired
	private ServiceLocator serviceLocator;

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
		return serviceLocator.getUserDao().getUserByUsername(username);
	}
	
	void setServiceLocator(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	public User saveUser(User user) {
		serviceLocator.getUserDao().createUser(user);
		return user;
	}
		
	public void updateUser(User user){
		serviceLocator.getUserDao().updateUser(user);
	}
	
	public User getUserByUuid(String uuid) {
		return serviceLocator.getUserDao().getUserByUuid(uuid);
	}

	public void updateUserPassword(User user, String password) {
		user.setPassword(password);
		updateUser(user);	
	}

	public void resetUuid(User user) {
		user.setUuid(UUID.randomUUID().toString());	
		updateUser(user);	
	}

	public List<ShoppingCart> getShoppingCartsByUser(User currentUser) {
		return serviceLocator.getUserDao().getShoppingCartsByUser(currentUser);
		
	}
}
