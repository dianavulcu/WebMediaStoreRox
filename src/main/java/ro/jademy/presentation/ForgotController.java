package ro.jademy.presentation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ro.jademy.domain.entities.User;
import ro.jademy.domain.service.MailService;
import ro.jademy.domain.service.UserService;

@Controller
public class ForgotController {

	@Autowired
	MailService mailService;

	@Autowired
	UserService userService;

	@RequestMapping("/forgotPassword")
	public ModelAndView forgotPassword() {

		return new ModelAndView("forgotPassword");
	}

	@RequestMapping("/generatePassword")
	public ModelAndView getNewPassword(String userName, String emailAddress, HttpServletRequest request) {
		if (userName == null || emailAddress == null || userName.trim().isEmpty() || emailAddress.trim().isEmpty()) {
			ModelAndView mv = new ModelAndView("forgotPassword");
			mv.addObject("errorMessage", "Campurile sunt obligatorii");
			return mv;
		}

		if (userService.getUser(userName) == null) {
			ModelAndView mv = new ModelAndView("forgotPassword");
			mv.addObject("errorMessage", "Userul nu exista.");
			return mv;
		}
		if (!(userService.getUser(userName).getEmailAddress().equals(emailAddress))) {
			ModelAndView mv = new ModelAndView("forgotPassword");
			mv.addObject("errorMessage", "Adresa de email nu corespunde cu userul.");
			return mv;
		}
		if ((userService.getUser(userName) != null)
				&& (userService.getUser(userName).getEmailAddress().equals(emailAddress))) {

			String url = request.getRequestURL().toString();
			System.out.println("Current URL is:" + url);
			
			
			mailService.sendNewPasswordMail(userService.getUser(userName), url);
			
			ModelAndView mv = new ModelAndView("login", "errorMessage", "Dati click pe linkul primit pe mail pentru a ve reseta parola.");
			return mv;
		}

		return null;
	}

	
	
	// @RequestMapping("/recoverPasswordRequest/{UUID}")
	// public ModelAndView recoverPasswordRequest(@PathVariable("uuid") String
	// uuid) {
	// ModelAndView mv = new ModelAndView("recoverPassword", "errorMessage",
	// "Logati-va din nou");
	// return mv;
	//
	// }

	@RequestMapping("/displayPassword/{uuid}")
	public ModelAndView displayMenuRecoverPassword(@PathVariable("uuid") String uuid) {
		return new ModelAndView("recoverPassword", "uuid", uuid);
	}

	@RequestMapping("/recoverPassword")
	public ModelAndView saveNewPassword(String uuid, String password, String repeatPassword) {
		if (password == null || repeatPassword == null || password.trim().isEmpty()
				|| repeatPassword.trim().isEmpty()) {
			ModelAndView mv = new ModelAndView("recoverPassword");
			mv.addObject("errorMessage", "Campurile sunt obligatorii");
			return mv;
		}
		if (!(password.equals(repeatPassword))) {
			ModelAndView mv = new ModelAndView("recoverPassword");
			mv.addObject("errorMessage", "Parolele nu se potrivesc");
			return mv;
		}
		UserService userService = new UserService();

		User currentUser = userService.getUuidService(uuid);
		currentUser.setPassword(repeatPassword);
		userService.updateUserService(currentUser);
		ModelAndView mv = new ModelAndView("login", "errorMessage", "Logati-va din nou");
		return mv;
	}
}
