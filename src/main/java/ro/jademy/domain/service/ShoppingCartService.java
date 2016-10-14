package ro.jademy.domain.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.jademy.domain.entities.ShoppingCart;
import ro.jademy.domain.entities.User;
import ro.jademy.domain.entities.UserType;
import ro.jademy.persistance.ShoppingCartDBDAO;

@Service
public class ShoppingCartService {

	@Autowired
	MailService mailService;

	@Autowired
	ShoppingCartDBDAO shoppingCartDBDAO;

	@Autowired
	UserService userService;

	public void saveShoppingCart(ShoppingCart shoppingCart, User user) {
		BigDecimal discountForUser = userService.getDiscountForUser(user);
		shoppingCartDBDAO.createCart(shoppingCart, user);
		mailService.sendCheckoutMail(shoppingCart, user, discountForUser);
	}

}
