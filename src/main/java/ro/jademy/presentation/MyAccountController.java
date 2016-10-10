package ro.jademy.presentation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ro.jademy.domain.entities.Media;
import ro.jademy.domain.entities.ShoppingCart;
import ro.jademy.domain.entities.User;
import ro.jademy.domain.service.ShoppingCartService;
import ro.jademy.domain.service.UserService;

@Controller
public class MyAccountController {

	@Autowired
	ShoppingCartService shoppingCartService;

	@Autowired
	UserService userService;

	@RequestMapping("/myAccount")
	public ModelAndView myAccount(HttpServletRequest request) {

		User currentUser = (User) request.getSession().getAttribute("currentUser");
		List<ShoppingCart> shoppingCarts;
		shoppingCarts = userService.getShoppingCartsByUser(currentUser);

		ModelAndView mv = new ModelAndView("myAccount", "shoppingCarts", shoppingCarts);
		return mv;
	}

}
