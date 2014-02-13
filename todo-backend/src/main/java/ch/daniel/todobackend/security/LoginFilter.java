package ch.daniel.todobackend.security;

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
import javax.servlet.http.HttpSession;

import ch.daniel.todobackend.dao.ApplicationProperties;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(urlPatterns={"/index.html", "/rest/*"})
public class LoginFilter implements Filter {

    public LoginFilter() {
    }

	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(ApplicationProperties.isTestMode()) {
			assertIsAuthenticated(request, response, chain);
		} else {
			chain.doFilter(request, response);
		}
	}

	private void assertIsAuthenticated(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		HttpSession session = httpRequest.getSession(true);
		Boolean authenticated = (Boolean) session.getAttribute("authenticated");
		if(isAuthenticated(authenticated)) {
			String requestURI = httpRequest.getRequestURI();
			session.setAttribute("requestUrl", requestURI);
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.html");
		} else {
			chain.doFilter(request, response);
		}
	}

	private boolean isAuthenticated(Boolean authenticated) {
		return authenticated == null || !authenticated;
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
