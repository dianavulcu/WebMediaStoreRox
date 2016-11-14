package ro.jademy.persistance;

import ro.jademy.domain.entities.ShoppingCart;
import ro.jademy.domain.entities.User;

public interface ShoppingCartDAO {
	void createCart(ShoppingCart shoppingCart, User user);
	ShoppingCart getShoppingCart(long savedCartId);
	
}
