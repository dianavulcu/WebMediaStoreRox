package ro.jademy.presentation;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ro.jademy.domain.entities.User;
import ro.jademy.domain.service.UserService;

@Controller
public class LoginController {
	private static final int TWO_WEEKS_IN_SECONDS = 2*7*24*60*60;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/")
	public String home() {
		return "login";
	}

	@RequestMapping("/login")
	public ModelAndView login(User user, Boolean rememberMe, HttpServletRequest request, HttpServletResponse response) {
		
		if (user.getUsername() != null && !user.getUsername().trim().isEmpty()) {
			User aUser = userService.checkPassword(user);
			if (aUser != null) {
				request.getSession().setAttribute("currentUser", aUser);
				String uuidCookie = UUID.randomUUID().toString();
				if(rememberMe != null && rememberMe){
					Cookie cookie = new Cookie("rememberMe", uuidCookie);
					cookie.setMaxAge(TWO_WEEKS_IN_SECONDS);
					aUser.setRememberMeId(uuidCookie);
					userService.updateUser(aUser);
					response.addCookie(cookie);
					
					
				}
				return new ModelAndView("redirect:mainMenu");
			}	
			return new ModelAndView("login", "errorMessage", "Autentificare gresita!");
		}
		return new ModelAndView("login", "errorMessage", "");
	}

}
