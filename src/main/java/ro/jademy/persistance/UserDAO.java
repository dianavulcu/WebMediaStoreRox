package ro.jademy.persistance;

import java.util.List;

import ro.jademy.domain.entities.ShoppingCart;
import ro.jademy.domain.entities.User;

public interface UserDAO {

	User getUserByUsername(String username);

	User getUserByUuid(String uuid);

	User getUserByRememberMeId(String value);

	void createUser(User user);

	void updateUser(User user);

	List<ShoppingCart> getShoppingCartsByUser(User currentUser);


}
