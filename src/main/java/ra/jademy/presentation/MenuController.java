package ra.jademy.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class MenuController {
	@RequestMapping("/mainMenu")
	public ModelAndView mainMenu(){
		return new ModelAndView("mainMenu");
	}

}
