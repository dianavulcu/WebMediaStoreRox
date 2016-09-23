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
		if (user.getUsername() != null && !user.getUsername().trim().isEmpty()) {
			User aUser = (new UserService()).checkPassword(user);
			if (aUser != null) {
				request.getSession().setAttribute("currentUser", aUser);
				return new ModelAndView("redirect:mainMenu");
			}	
			return new ModelAndView("login", "errorMessage", "Autentificare gresita!");
		}
		return new ModelAndView("login", "errorMessage", "");
	}	
	
}
