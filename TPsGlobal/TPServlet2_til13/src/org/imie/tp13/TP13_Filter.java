package org.imie.tp13;

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

import org.imie.transverse.DTO.Personne;

/**
 * Servlet Filter implementation class TP13_Filter
 */
@WebFilter(
		urlPatterns={"/*"}
)
public class TP13_Filter implements Filter {

    /**
     * Default constructor. 
     */
    public TP13_Filter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		String url = httpRequest.getRequestURL().toString();
		
		Personne identified = (Personne) session.getAttribute("identified");
//		System.out.println("authentifie= ".concat(identified == null ? "null":identified));
		if(identified != null){
			// pass the request along the filter chain
			System.out.println("test filtre passant");
			System.out.println(url);
			chain.doFilter(httpRequest, httpResponse);
		}
		else if(url.contains(".css") || url.contains("AuthentTP13")) {
			// pass the request along the filter chain
			System.out.println("test filtre css ou authentification");
			System.out.println(url);
			chain.doFilter(httpRequest, httpResponse);
		}
		else{
			System.out.println("redirection authent");
			httpResponse.sendRedirect("./AuthentTP13");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
