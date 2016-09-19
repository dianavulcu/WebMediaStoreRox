package ra.jademy.presentation;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ra.jademy.domain.Media;
import ra.jademy.domain.MediaService;
import ra.jademy.domain.ProductType;

@Controller
public class ListController {

	@RequestMapping("/productList/{productType}")
	public ModelAndView productList(@PathVariable("productType") ProductType productType) {
		MediaService md = new MediaService();
		List<? extends Media> aList = md.getAllMedia(productType);
		return new ModelAndView("productList", "aList", aList);
	}
}
