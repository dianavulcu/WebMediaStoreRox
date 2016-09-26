package ro.jademy.presentation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.jademy.domain.entities.Media;
import ro.jademy.domain.entities.ProductType;
import ro.jademy.domain.entities.ShoppingCart;
import ro.jademy.domain.entities.User;
import ro.jademy.domain.service.MediaService;
import ro.jademy.domain.service.ShoppingCartService;

@Controller
public class CartController {
	
	@Autowired
	ShoppingCartService shoppingCartService;   
	
	@RequestMapping("/addProductToCart")
	public ModelAndView addProductToCart(String productCode, int cantitate, ProductType productType,
			HttpServletRequest request) {
		if (cantitate == 0) {
			ModelAndView mv = new ModelAndView("redirect:productList/" + productType.toString());
			return mv;
		}
		MediaService mediaService = new MediaService();
		Media media = mediaService.getProductByProductTypeAndCode(productType, productCode);
		ShoppingCart shoppingCart = (ShoppingCart)request.getSession().getAttribute("shoppingCart");
		if(shoppingCart == null){
			shoppingCart = new ShoppingCart();
			request.getSession().setAttribute("shoppingCart", shoppingCart);
		}
		
		shoppingCart.addToCart(media, cantitate);
		ModelAndView mv = new ModelAndView("redirect:productList/" + productType.toString());
		return mv;

	}
	@RequestMapping("/displayCart")
	public ModelAndView displayCart(){
		return new ModelAndView("displayCart");
	}
	
	@RequestMapping("/checkout")
	public ModelAndView checkout(HttpServletRequest request){
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("shoppingCart");
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		shoppingCartService.saveShoppingCart(shoppingCart, currentUser);
		request.getSession().setAttribute("shoppingCart", new ShoppingCart());
		return new ModelAndView("redirect:/mainMenu");
	}

}
