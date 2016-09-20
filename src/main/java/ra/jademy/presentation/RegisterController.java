package ra.jademy.presentation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ra.jademy.domain.MailService;
import ra.jademy.domain.User;
import ra.jademy.domain.UserService;

@Controller
public class RegisterController {
	
	@Autowired
	MailService mailService;
	
	@RequestMapping("/register")
	public ModelAndView displayMenuRegister() {

		return new ModelAndView("registerMenu");
	}

	@RequestMapping("/registerUser")
	public ModelAndView saveNewUser(String userName, String password, String repeatPassword, String emailAddress) {

		
		if(userName == null || password == null || repeatPassword == null || userName.trim().isEmpty() || password.trim().isEmpty() || repeatPassword.trim().isEmpty()){
			ModelAndView mv = new ModelAndView("registerMenu");
			mv.addObject("errorMessage", "Campurile sunt obligatorii");
			return mv;
		}
		
			
		if (!(password.equals(repeatPassword))) {
			ModelAndView mv = new ModelAndView("registerMenu");
			mv.addObject("errorMessage", "Parolele nu se potrivesc");
			return mv;
		}
		//UserService userService = new UserService();
		if((new UserService()).getUser(userName)!= null  ){
			ModelAndView mv = new ModelAndView("registerMenu");
			mv.addObject("errorMessage", "Userul exista deja" );
			return mv;
			
		}
		
//		sendMail();
		
		User savedUser =  (new UserService()).saveUser(new User(userName , password, emailAddress));
		
		ModelAndView mv = new ModelAndView("login", "errorMessage", "Userul a fost creat cu succes.");
		return mv;
	}

//	private void sendMail() {
//		mailService.sendMail("cui_trimite@yahoo.com", "Subiect test", "Textul", false);
//	}
}


