package ro.jademy.domain.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.jademy.domain.entities.ShoppingCart;
import ro.jademy.domain.entities.User;
import ro.jademy.domain.entities.UserType;

@Service
public class UserService {

	@Autowired
	private ServiceLocator serviceLocator;

	void setMockServiceLocator(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

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

	public void updateUser(User user) {
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
		// logica
		return serviceLocator.getUserDao().getShoppingCartsByUser(currentUser);

	}

	public User getUserByRememberMeId(String value) {
		return serviceLocator.getUserDao().getUserByRememberMeId(value);

	}

	public int getLoyaltyPointsForUser(User user) {
		List<ShoppingCart> shoppingCarts = serviceLocator.getUserDao().getShoppingCartsByUser(user);
		int lp = 0;
		UserType userType = UserType.NEW_RENTAL_CLIENT;
		for (ShoppingCart shoppingCart : shoppingCarts) {
			double shoppingCartPrice = shoppingCart.getTotalPrice();
			if (userType == UserType.NEW_RENTAL_CLIENT) {
				shoppingCartPrice *= 0.95;
				userType = UserType.REGULAR;
			}
			if (userType == UserType.GOLD) {
				shoppingCartPrice *= 0.99;
			}
			if (userType == UserType.PLATINUM) {
				shoppingCartPrice *= 0.97;
			}
			if (shoppingCartPrice > 30) {
				lp += 2;
			} else if (shoppingCartPrice > 10) {
				lp += 1;
			}
			if (lp > 100) {
				userType = UserType.PLATINUM;
			} else if (lp > 10) {
				userType = UserType.GOLD;
			}	
		}
		return lp;
	}
}
