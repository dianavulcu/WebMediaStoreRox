package ro.jademy.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ro.jademy.domain.entities.User;
@Controller
public class MenuController {
	@RequestMapping("/mainMenu")
	public ModelAndView mainMenu(User aUser){
		return new ModelAndView("mainMenu");
	}

}
