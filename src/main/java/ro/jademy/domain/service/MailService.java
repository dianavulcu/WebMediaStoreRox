package ro.jademy.domain.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import ro.jademy.domain.entities.ShoppingCart;
import ro.jademy.domain.entities.User;

@Service
public class MailService {

	private static final String CHARSET_UTF8 = "UTF-8";

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	@Autowired
	private UserService userService;
	
	@Async
	public void sendRegistrationMail(User user) {
		try {
			MimeMessage mail = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail, true);
			helper.setTo(user.getEmailAddress());
			helper.setSubject(user.getUsername() + ", welcome to roxmediastore :)");
			Map model = new HashMap<>();
			model.put("user", user);

			helper.setText(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "registration-confirmation.vm",
					CHARSET_UTF8, model), true);
			javaMailSender.send(mail);
		} catch (Exception e) {
			throw new MailNotificationException("Cannot send mail", e);
		}
	}
	
	@Async
	public void sendPasswordResetMail(User user, String url) {
		try {
			MimeMessage mail = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail, true);
			helper.setTo(user.getEmailAddress());
			helper.setSubject(user.getUsername() + ", you requested a new password");

			Map model = new HashMap<>();

			String code = UUID.randomUUID().toString();
			user.setUuid(code);
			userService.updateUser(user);

			url = url + "/" + user.getUuid();

			model.put("user", user);
			model.put("url", url);

			helper.setText(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "forgot-password.vm",
					CHARSET_UTF8, model), true);
			javaMailSender.send(mail);
		} catch (Exception e) {
			throw new MailNotificationException("Cannot send email", e);
		}
	}
	
	@Async
	public void sendNewPasswordMail(User user) {
		try {
			MimeMessage mail = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail, true);
			helper.setTo(user.getEmailAddress());
			helper.setSubject(user.getUsername() + ", here is your new password");

			Map model = new HashMap<>();

			model.put("user", user);

			helper.setText(
					VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "new-password.vm", CHARSET_UTF8, model),
					true);
			javaMailSender.send(mail);
		} catch (Exception e) {
			throw new MailNotificationException("Cannot send email", e);
		}
	}
	
	@Async
	public void sendCheckoutMail(ShoppingCart shoppingCart, User user) {
		try {
			MimeMessage mail = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail, true);
			helper.setTo(user.getEmailAddress());
			helper.setSubject(user.getUsername() + ", here is you shopping cart, total = " + shoppingCart.getTotalPrice());

			Map model = new HashMap<>();
			model.put("user", user);
			model.put("shoppingCartitems", shoppingCart.getCartItems());
			model.put("total", shoppingCart.getTotalPrice());

			helper.setText(
					VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "checkout.vm", CHARSET_UTF8, model),
					true);
			javaMailSender.send(mail);
		} catch (Exception e) {
			throw new MailNotificationException("Cannot send mail", e);
		}

	}

}
