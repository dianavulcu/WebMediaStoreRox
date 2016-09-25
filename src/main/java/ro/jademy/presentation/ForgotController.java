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

		User user = userService.getUserByUsername(userName);

		if (user == null) {
			ModelAndView mv = new ModelAndView("forgotPassword");
			mv.addObject("errorMessage", "Userul nu exista.");
			return mv;
		}
		if (!(user.getEmailAddress().equals(emailAddress))) {
			ModelAndView mv = new ModelAndView("forgotPassword");
			mv.addObject("errorMessage", "Adresa de email nu corespunde cu adresa acestui user.");
			return mv;
		}

		String url = request.getRequestURL().toString();
		mailService.sendPasswordResetMail(user, url);

		ModelAndView mv = new ModelAndView("login");
		mv.addObject("errorMessage", "Dati click pe linkul primit pe email pentru a va reseta parola.");
		return mv;

	}

	@RequestMapping("/generatePassword/{uuid}")
	public ModelAndView generatePasswordAfterUUID(@PathVariable("uuid") String uuid) {
		User user = userService.getUserByUuid(uuid);
		return new ModelAndView("resetPassword", "user", user);
	}

	@RequestMapping("/resetPassword")
	public ModelAndView saveNewPassword(String uuid, String password, String repeatPassword) {
		User user = userService.getUserByUuid(uuid);
		if (password == null || repeatPassword == null || password.trim().isEmpty()
				|| repeatPassword.trim().isEmpty()) {
			ModelAndView mv = new ModelAndView("resetPassword");
			mv.addObject("user", user);
			mv.addObject("errorMessage", "Campurile sunt obligatorii !");
			return mv;
		}
		if (!(password.equals(repeatPassword))) {
			ModelAndView mv = new ModelAndView("resetPassword");
			mv.addObject("user", user);
			mv.addObject("errorMessage", "Parolele nu se potrivesc");
			return mv;
		}

		userService.updateUserPassword(user, password);
		userService.resetUuid(user);
		mailService.sendNewPasswordMail(user);

		return new ModelAndView("login", "errorMessage", "V-am trimis email cu noua parola. Logati-va din nou");
	}
}
