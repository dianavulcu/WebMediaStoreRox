package ro.jademy.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.jademy.domain.entities.ShoppingCart;
import ro.jademy.domain.entities.User;
import ro.jademy.persistance.ShoppingCartDBDAO;

@Service
public class ShoppingCartService {
	
	@Autowired
	MailService mailService;
	
	@Autowired
	ShoppingCartDBDAO shoppingCartDBDAO;
	
	public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart, User user){
		mailService.sendCheckoutMail(shoppingCart, user);
		long savedCartId = shoppingCartDBDAO.createCart(shoppingCart, user);
		return shoppingCartDBDAO.getShoppingCart(savedCartId);
	}
}