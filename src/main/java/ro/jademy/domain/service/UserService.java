package ro.jademy.domain.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static ro.jademy.domain.entities.PriceCategory.*;
import static ro.jademy.domain.entities.AgeCategory.*;
import ro.jademy.domain.entities.ShoppingCart;
import ro.jademy.domain.entities.User;
import ro.jademy.domain.entities.UserType;
import ro.jademy.persistance.UserDBDAO;

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
		if (user.getUserType() == null) {
			user.setUserType(UserType.NEW_CLIENT);
		}
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

	public void updateUserUserType(User user, UserType userType) {
		user.setUserType(userType);
		updateUser(user);
	}

	public List<ShoppingCart> getShoppingCartsByUser(User currentUser) {
		return serviceLocator.getUserDao().getShoppingCartsByUser(currentUser);
	}

	public User getUserByRememberMeId(String value) {
		return serviceLocator.getUserDao().getUserByRememberMeId(value);
	}

	public int getLoyaltyPointsForUser(User user) {
		List<ShoppingCart> shoppingCarts = serviceLocator.getUserDao().getShoppingCartsByUser(user);
		int lp = 0;
		for (ShoppingCart shoppingCart : shoppingCarts) {
			double shoppingCartPrice = shoppingCart.getTotalPrice();
			if (shoppingCartPrice > 30) {
				lp += 3;
			} else if (shoppingCartPrice > 10) {
				lp += 2;
			} 
			lp += shoppingCart.getLoyaltyPointsForMediaCategories();		
		}
		return lp;
	}

	public UserType getUserTypeForUser(int lp) {
		UserType userType = UserType.NEW_CLIENT;
		if (lp > 100) {
			userType = UserType.PLATINUM;
		} else if (lp > 10) {
			userType = UserType.GOLD;
		} else if (lp > 0) {
			userType = UserType.REGULAR;
		}
		return userType;
	}

	public BigDecimal getDiscountForUser(User user) {
		int lp = getLoyaltyPointsForUser(user);
		UserType userType = getUserTypeForUser(lp);
		BigDecimal shoppingCartDiscount = userType.getDiscount();
		if (userType == UserType.NEW_CLIENT) {
			userType = UserType.REGULAR;
			updateUserUserType(user, userType);
		}
		return shoppingCartDiscount;
	}

}
