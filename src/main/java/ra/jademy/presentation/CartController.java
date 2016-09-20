package ra.jademy.presentation;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ra.jademy.domain.entities.CartItem;
import ra.jademy.domain.entities.Media;
import ra.jademy.domain.entities.ProductType;
import ra.jademy.domain.entities.ShoppingCart;

import ra.jademy.domain.service.MediaService;

@Controller
public class CartController {
	
	@RequestMapping("/addProductToCart")
	public ModelAndView addProductToCart(String productCode, int cantitate, ProductType productType, HttpServletRequest request){
		MediaService mediaService = new MediaService ();
		Media media = mediaService.getProductByCode(productCode);
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.addToCart(media, cantitate);
		request.getSession().setAttribute("cart", shoppingCart);
		ModelAndView mv = new ModelAndView("redirect:productList/"+productType.toString());

		
		return mv;
		
	}
	

}
