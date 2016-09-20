package ra.jademy.presentation;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ra.jademy.domain.entities.Media;
import ra.jademy.domain.service.MediaService;
import ra.jademy.domain.entities.ProductType;

@Controller
public class ListController {

	@RequestMapping("/productList/{productType}")
	public ModelAndView productList(@PathVariable("productType") ProductType productType) {
		MediaService md = new MediaService();
		List<? extends Media> aList = md.getAllMedia(productType);
		ModelAndView mv = new ModelAndView("productList", "aList", aList);
		mv.addObject("productType", productType);
		return mv;
	}
}
