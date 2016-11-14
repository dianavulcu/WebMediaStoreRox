package ro.jademy.domain.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.jademy.domain.entities.ShoppingCart;
import ro.jademy.domain.entities.User;

@Service
public class ShoppingCartService {

	@Autowired
	private ServiceLocator serviceLocator;
	void setMockServiceLocator(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	void setServiceLocator(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}
	
	@Autowired
	MailService mailService;

	@Autowired
	UserService userService;

	public void saveShoppingCart(ShoppingCart shoppingCart, User user) {
		BigDecimal discountForUser = userService.getDiscountForUser(user);
		serviceLocator.getShoppingCartDao().createCart(shoppingCart, user);
		mailService.sendCheckoutMail(shoppingCart, user, discountForUser);
	}

}
