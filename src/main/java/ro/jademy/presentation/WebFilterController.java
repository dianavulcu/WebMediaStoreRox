package ro.jademy.presentation;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.jademy.domain.entities.User;
import ro.jademy.domain.service.UserService;

@WebFilter(value = "/*", description = "Session Checker Filter")
@Component

public class WebFilterController implements Filter {
	@Autowired
	UserService userService;

	private FilterConfig config = null;

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		config.getServletContext().log("Initializing SessionCheckerFilter");
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if (request.getRequestURI().endsWith("/login") || request.getRequestURI().endsWith("/header.jsp")
				|| request.getRequestURI().endsWith("/footer.jsp") || request.getRequestURI().endsWith("/login.jsp")
				|| request.getRequestURI().endsWith("/favicon.ico") || request.getRequestURI().endsWith("/register")
				|| request.getRequestURI().endsWith("/registerMenu.jsp")
				|| request.getRequestURI().endsWith("/registerUser")
				|| request.getRequestURI().endsWith("/forgotPassword")
				|| request.getRequestURI().endsWith("/forgotPassword.jsp")
				|| request.getRequestURI().endsWith("/generatePassword/*")
				|| request.getRequestURI().endsWith("/resetPassword")
				|| request.getRequestURI().endsWith("/resetPassword.jsp")) {

		} else if (currentUser == null) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if ("rememberMe".equals(cookie.getName())) {
						User user = userService.getUserByRememberMeId(cookie.getValue());
						if (user != null && user.getRememberMeDate().plusDays(14).isAfter(LocalDateTime.now())) {
							request.getSession().setAttribute("currentUser", user);
							chain.doFilter(req, res);
							return;
						}
					}
				}
			}
			response.sendRedirect(request.getContextPath() + "/login");
		}

		chain.doFilter(req, res);

	}

	public void destroy() {
		config.getServletContext().log("Destroying SessionCheckerFilter");
	}
}
