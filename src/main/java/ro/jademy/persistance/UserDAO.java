package ro.jademy.persistance;

import java.util.List;

import ro.jademy.domain.entities.ShoppingCart;
import ro.jademy.domain.entities.User;

public interface UserDAO {

	User getUserByUsername(String username);

	void createUser(User user);

	void updateUser(User user);

	User getUserByUuid(String uuid);

	List<ShoppingCart> getShoppingCartsByUser(User currentUser);

	User getUserByRememberMeId(String value);

}
