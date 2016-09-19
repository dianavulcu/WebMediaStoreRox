package ra.jademy.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ra.jademy.domain.MediaService;

@Controller
public class CartController {
	
	@RequestMapping("/addProductToCart")
	public ModelAndView addProductToCart(String productCode, int cantitate){
		MediaService mediaService = new MediaService ();
	//	Media media = mediaService.getProductByCode(productCode);
		ModelAndView mv = new ModelAndView("redirect:productList");
		return mv;
		
	}
	

}
