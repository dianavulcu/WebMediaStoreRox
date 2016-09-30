package ro.jademy.presentation;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ro.jademy.domain.entities.Media;
import ro.jademy.domain.entities.ProductType;
import ro.jademy.domain.service.MediaService;

@Controller
public class ListController {
	
private static enum SortOrder{
	ASC, DESC;
}

private static enum SortColumns{
	TITLE, DESCRIPTION, PRICE, GENRE;
}
	@Autowired
	MediaService mediaService;
	
	@RequestMapping("/productList/{productType}")
	public ModelAndView productList(@PathVariable("productType") ProductType productType, SortOrder order, SortColumns sort ) {
		List<? extends Media> aList = mediaService.getAllMedia(productType);
		Comparator<Media> comp = new Comparator<Media>() {
			public int compare(Media o1, Media o2) {
				SortColumns sortColumns2 = (sort == null ? SortColumns.TITLE:sort);
				SortOrder sortOrder2 = (order == null ? SortOrder.ASC : order);	
				int signOrder = (sortOrder2 == SortOrder.ASC ? 1 : -1);	
				switch(sortColumns2) {
					case DESCRIPTION:
						return o1.getDescription().compareToIgnoreCase(o2.getDescription()) * signOrder;
					case GENRE:
						return (o1.getGenre().name()).compareToIgnoreCase(o2.getGenre().name())* signOrder;
					case PRICE:
						return Double.valueOf(o1.getPrice()).compareTo(o2.getPrice())*signOrder;
					case TITLE:
						return o1.getTitle().compareToIgnoreCase(o2.getTitle())*signOrder;
					default:
						throw new IllegalArgumentException("Unknown sort order: "+ sortColumns2);
				}
			}
		};
		Collections.sort(aList, comp);

		ModelAndView mv = new ModelAndView("productList", "aList", aList);
		mv.addObject("productType", productType);
		
		return mv;
	}
}
	

