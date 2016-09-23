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

		if (userService.getUserByUsername(userName) == null) {
			ModelAndView mv = new ModelAndView("forgotPassword");
			mv.addObject("errorMessage", "Userul nu exista.");
			return mv;
		}
		if (!(userService.getUserByUsername(userName).getEmailAddress().equals(emailAddress))) {
			ModelAndView mv = new ModelAndView("forgotPassword");
			mv.addObject("errorMessage", "Adresa de email nu corespunde cu userul.");
			return mv;
		}
		if ((userService.getUserByUsername(userName) != null)
				&& (userService.getUserByUsername(userName).getEmailAddress().equals(emailAddress))) {

			String url = request.getRequestURL().toString();
			System.out.println("Current URL is:" + url);

			mailService.sendPasswordResetMail(userService.getUserByUsername(userName), url);

			ModelAndView mv = new ModelAndView("login", "errorMessage",
					"Dati click pe linkul primit pe mail pentru a ve reseta parola.");
			return mv;
		}

		return null;
	}

	@RequestMapping("/generatePassword/{uuid}")
	public ModelAndView generatePasswordAfterUUID(@PathVariable("uuid") String uuid) {
		User user = userService.getUserByUuid(uuid);
		return new ModelAndView("resetPassword", "user", user);
	}

	@RequestMapping("/resetPassword")
	public ModelAndView saveNewPassword(String uuid, String password, String repeatPassword) {
		if (password == null || repeatPassword == null || password.trim().isEmpty()
				|| repeatPassword.trim().isEmpty()) {
			return new ModelAndView("resetPassword", "errorMessage", "Campurile sunt obligatorii");
		}
		if (!(password.equals(repeatPassword))) {
			return new ModelAndView("resetPassword", "errorMessage", "Parolele nu se potrivesc");
		}
		
		User user = userService.getUserByUuid(uuid);
		userService.updateUserPassword(user, password);
		userService.resetUuid(user);
		mailService.sendNewPasswordMail(user);
		
		return new ModelAndView("login", "errorMessage", "V-am trimis mail cu noua parola. Logati-va din nou");
	}
}
