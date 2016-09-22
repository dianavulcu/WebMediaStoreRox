package ra.jademy.presentation;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ra.jademy.domain.service.MailService;
import ra.jademy.domain.entities.User;
import ra.jademy.domain.service.UserService;

@Controller
public class ForgotController {
	
	@Autowired
	MailService mailService;
	
	@RequestMapping("/forgotPassword")
	public ModelAndView displayMenuForgotPassword() {

		return new ModelAndView("forgotPasswordMenu");
	}
	
	@RequestMapping("/generatePassword")
	public ModelAndView getNewPassword(String userName, String emailAddress) {
		if(userName == null || emailAddress == null || userName.trim().isEmpty() || emailAddress.trim().isEmpty()){
			ModelAndView mv = new ModelAndView("forgotPasswordMenu");
			mv.addObject("errorMessage", "Campurile sunt obligatorii");
			return mv;
		}
		
		UserService userService = new UserService();
		if(userService.getUser(userName)== null){
			ModelAndView mv = new ModelAndView("forgotPasswordMenu");
			mv.addObject("errorMessage", "Userul nu exista.");
			return mv;
		}
		if(!(userService.getUser(userName).getEmailAddress().equals(emailAddress))){
			ModelAndView mv = new ModelAndView("forgotPasswordMenu");
			mv.addObject("errorMessage", "Adresa de email nu corespunde cu userul.");
			return mv;			
		}
		if((userService.getUser(userName)!= null) && (userService.getUser(userName).getEmailAddress().equals(emailAddress))){
			sendMail(userService.getUser(userName));
			ModelAndView mv = new ModelAndView("login", "errorMessage", "Introduceti noua parola primita pe mail.");
			return mv;
		}
		
		return null;
	}
	
	private void sendMail(User user) {
		String code = UUID.randomUUID().toString();
		user.setUUID(code);
		UserService userService = new UserService();
		userService.updateUserService(user);
		mailService.sendMail(user.getEmailAddress(), "Parola noua", "Schimba parola la linkul: http://localhost:8080/displayPassword/" + code, false);
	}
	
//	@RequestMapping("/recoverPasswordRequest/{UUID}")
//	public ModelAndView recoverPasswordRequest(@PathVariable("uuid") String uuid) {
//		ModelAndView mv = new ModelAndView("recoverPassword", "errorMessage", "Logati-va din nou");
//		return mv;
//		
//	}
	
	@RequestMapping("/displayPassword/{uuid}")
	public ModelAndView displayMenuRecoverPassword(@PathVariable("uuid") String uuid) {
		return new ModelAndView("recoverPassword", "uuid", uuid);
	}
	
	@RequestMapping("/recoverPassword")
	public ModelAndView saveNewPassword(String uuid, String password, String repeatPassword) {
		//uuid = importFile.getProperty("user[" + i + "].username");
		if(password == null || repeatPassword == null ||password.trim().isEmpty() || repeatPassword.trim().isEmpty()){
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
	
		//password = repeatPassword;
		User currentUser = userService.getUuidService(uuid);
		currentUser.setPassword(repeatPassword);
		userService.updateUserService(currentUser);
		ModelAndView mv = new ModelAndView("login", "errorMessage", "Logati-va din nou");
		return mv;
	}
}
