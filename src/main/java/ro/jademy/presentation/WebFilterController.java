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

@WebFilter(urlPatterns = { "/*" }, description = "Session Checker Filter")

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

		if (!request.getRequestURI().endsWith("/") && request.getSession().getAttribute("currentUser") == null) {
			response.sendRedirect(request.getContextPath() + "/");
		}

		chain.doFilter(req, res);
	}

	public void destroy() {
		config.getServletContext().log("Destroying SessionCheckerFilter");
	}
}
