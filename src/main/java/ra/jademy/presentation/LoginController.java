package ra.jademy.presentation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ra.jademy.domain.User;
import ra.jademy.domain.UserService;

@Controller
public class LoginController {

	@RequestMapping("/")
	public String home() {
		System.out.println("home controller started");
		return "login";
	}
	
	@RequestMapping("/login")
	public ModelAndView login(User user, HttpServletRequest request){
		UserService userService = new UserService();
		
		if(userService.checkPassword(user)){
			User aUser  = userService.getUser(user.getUsername());
			request.getSession().setAttribute("currentUser", aUser);
			return new ModelAndView("redirect:mainMenu");
		}
		return new ModelAndView ("login", "errorMessage", "Autentificare gresita!");
	}
	
	
	
}
