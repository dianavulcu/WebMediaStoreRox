package ro.jademy.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.jademy.domain.entities.ShoppingCart;
import ro.jademy.domain.entities.User;
import ro.jademy.persistance.ShoppingCartDAO;

@Service
public class ShoppingCartService {
	
	@Autowired
	MailService mailService;
	
	@Autowired
	ShoppingCartDAO shoppingCartDAO;
	
	public void saveShoppingCart(ShoppingCart shoppingCart, User user){
		//mailService.sendMail(user.getEmailAddress(), "shopping cart","cosul este ..." , false);
		shoppingCartDAO.createCart(shoppingCart, user);
	}
}