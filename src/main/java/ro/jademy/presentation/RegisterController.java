package ro.jademy.presentation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ro.jademy.domain.entities.User;
import ro.jademy.domain.entities.UserType;
import ro.jademy.domain.service.MailService;
import ro.jademy.domain.service.UserService;

@Controller
public class RegisterController {

	@Autowired
	MailService mailService;

	@Autowired
	UserService userService;

	@RequestMapping("/register")
	public ModelAndView displayMenuRegister() {

		return new ModelAndView("registerMenu");
	}

	@RequestMapping("/registerUser")
	public ModelAndView saveNewUser(String userName, String password, String repeatPassword, 
			String firstName, String lastName, String emailAddress) {

		if (userName == null || password == null || repeatPassword == null || userName.trim().isEmpty()
				|| password.trim().isEmpty() || repeatPassword.trim().isEmpty()) {
			ModelAndView mv = new ModelAndView("registerMenu");
			mv.addObject("errorMessage", "Campurile marcate cu * sunt obligatorii");
			return mv;
		}

		if (!(password.equals(repeatPassword))) {
			ModelAndView mv = new ModelAndView("registerMenu");
			mv.addObject("errorMessage", "Parolele nu se potrivesc");
			return mv;
		}
		
		Pattern pattern = Pattern.compile("\\w+");
		Matcher matcher = pattern.matcher(userName);
		if( ! matcher.matches()) {
			ModelAndView mv = new ModelAndView("registerMenu");
			mv.addObject("errorMessage", "Username-ul conține caractere ilegale.");
			return mv;
		}
		
		Pattern pattern2 = Pattern.compile(".*" + userName + ".*");
		Matcher matcher2 = pattern2.matcher(password);
		if(matcher2.matches()) {
			ModelAndView mv = new ModelAndView("registerMenu");
			mv.addObject("errorMessage", "Username-ul nu trebuie să se regăsească în parolă.");
			return mv;
		}

		if (userService.getUserByUsername(userName) != null) {
			ModelAndView mv = new ModelAndView("registerMenu");
			mv.addObject("errorMessage", "Acest user exista deja");
			return mv;
		}

		User savedUser = userService.saveUser(new User(userName, password, emailAddress, UserType.REGULAR, firstName, lastName));
		mailService.sendRegistrationMail(savedUser);
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("errorMessage", "Userul a fost creat cu succes. Verifica-ti emailul.");
		mv.addObject("username", userName);
		return mv;
	}

}
