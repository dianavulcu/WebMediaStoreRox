package ro.jademy.presentation;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import ro.jademy.domain.entities.User;

@WebFilter(value = "/*", description = "Session Checker Filter")
@Component

public class WebFilterController implements Filter {

	private FilterConfig config = null;

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		config.getServletContext().log("Initializing SessionCheckerFilter");
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		System.out.println("Request URI" + request.getRequestURI().toString()); //debug - arata calea
		User currentUser =(User) request.getSession().getAttribute("currentUser");
		if (request.getRequestURI().endsWith("/login") ||
			request.getRequestURI().endsWith("/header.jsp") ||
			request.getRequestURI().endsWith("/footer.jsp") ||
			request.getRequestURI().endsWith("/login.jsp") ||
			request.getRequestURI().endsWith("/favicon.ico") ||
			request.getRequestURI().endsWith("/register") ||
			request.getRequestURI().endsWith("/registerMenu.jsp") ||
			request.getRequestURI().endsWith("/registerUser") ||
			request.getRequestURI().endsWith("/forgotPassword") ||
			request.getRequestURI().endsWith("/forgotPassword.jsp") ||
			request.getRequestURI().endsWith("/generatePassword/*") ||
			request.getRequestURI().endsWith("/resetPassword") ||
			request.getRequestURI().endsWith("/resetPassword.jsp")) {
			System.out.println("Am intrat pe url-urile interzise"); //debug - arata ca intra pe exceptii de uri
		} else if (currentUser == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			}

		chain.doFilter(req, res);
	}

	public void destroy() {
		config.getServletContext().log("Destroying SessionCheckerFilter");
	}
}
