package ra.jademy.presentation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ra.jademy.domain.entities.Media;
import ra.jademy.domain.service.MediaService;
import ra.jademy.domain.entities.ProductType;
import ra.jademy.domain.entities.ShoppingCart;

@Controller
public class ListController {

	@RequestMapping("/productList/{productType}")
	public ModelAndView productList(@PathVariable("productType") ProductType productType, HttpServletRequest request) {
		List<? extends Media> aList = (new MediaService ()).getAllMedia(productType);
		ModelAndView mv = new ModelAndView("productList", "aList", aList);
		mv.addObject("productType", productType);
		return mv;
	}
}
