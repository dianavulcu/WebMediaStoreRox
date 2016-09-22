package ro.jademy.presentation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ro.jademy.domain.entities.User;
import ro.jademy.domain.service.UserService;

@Controller
public class LoginController {
	@RequestMapping("/")
	public String home() {
		return "login";
	}
	
	@RequestMapping("/login")
	public ModelAndView login(User user, HttpServletRequest request){
		UserService userService = new UserService();
		
		if(userService.checkPassword(user)){
			User aUser  = userService.getUser(user.getUsername());
			request.getSession().setAttribute("aUser", aUser);
			ModelAndView mv = new ModelAndView("/mainMenu");
			mv.addObject("aUser", aUser);
			return mv;
		}
		return new ModelAndView ("login", "errorMessage", "Autentificare gresita!");
	}
	
	
	
}
