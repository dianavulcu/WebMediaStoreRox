package ro.jademy.presentation;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ro.jademy.domain.entities.Media;
import ro.jademy.domain.entities.ProductType;
import ro.jademy.domain.entities.ShoppingCart;
import ro.jademy.domain.entities.User;
import ro.jademy.domain.service.MediaService;

@Controller
public class CartController {
	
	ShoppingCart shoppingCart = new ShoppingCart();
	@RequestMapping("/addProductToCart")
	public ModelAndView addProductToCart(String productCode, int cantitate, ProductType productType, User aUser, 
			HttpServletRequest request) {
		if(cantitate == 0){
			ModelAndView mv = new ModelAndView("redirect:productList/" + productType.toString());
			return mv;
		}
		MediaService mediaService = new MediaService();
		Media media = mediaService.getProductByProductTypeAndCode(productType, productCode);
		shoppingCart.addToCart(media, cantitate);
		double price = shoppingCart.getTotalPrice();
		int totalItems = shoppingCart.getTotalItems();
		request.getSession().setAttribute("cart", shoppingCart);
		request.getSession().setAttribute("price", price);
		request.getSession().setAttribute("totalItems", totalItems);
		ModelAndView mv = new ModelAndView("redirect:productList/" + productType.toString());
		return mv;

	}

}
