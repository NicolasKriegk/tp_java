package org.imie.filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.imie.interfaceservice.IGestionEcoleService;
import org.imie.transverse.DTO.Personne;
import org.imie.transverse.exception.ServiceException;
import org.imie.transverse.factory.ConcreteSingletonFactory;
import org.jboss.resteasy.spi.HttpRequest;

/**
 * Servlet Filter implementation class TP5_Authent
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, urlPatterns = { "/*" })
public class TP5_Authent implements Filter {

	/**
	 * Default constructor.
	 */
	public TP5_Authent() {
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
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		Boolean authentified = false;
		Boolean authentifying = false;
		Boolean resourceToScan = true;
		Boolean requestInterupted = false;
		if (httpServletRequest.getRequestURI().contains("css") || httpServletRequest.getRequestURI().contains("TP14")) {
			resourceToScan = false;
		}
		if (httpServletRequest.getRequestURI().contains("TP13")) {
			authentifying = true;
			//Interception forcée des POST des URL contenant TP13
			if (httpServletRequest.getMethod().equals("POST")
					&& httpServletRequest.getParameter("valider") != null) {
				String inputNom = request.getParameter("inputNom");
				String inputPassword = request.getParameter("inputPassword");
				Personne searchPersonne = new Personne();
				searchPersonne.setNom(inputNom);
				searchPersonne.setPassw(inputPassword);
				IGestionEcoleService gestionEcoleService = ConcreteSingletonFactory
						.getInstance().creategGestionEcoleService();
				Personne authPersonne = null;
				try {
					authPersonne = gestionEcoleService
							.verifierAuthPersonne(searchPersonne);
				} catch (ServiceException e) {
					request.setAttribute("messageException", e.getMessage());
				}
				if (authPersonne != null) {
					httpServletRequest.getSession().setAttribute(
							"authentifiedPersonne", authPersonne);
					String uri = (String) httpServletRequest.getSession()
							.getAttribute("originURL");
					httpServletResponse.sendRedirect(uri);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("TP13_View");
					dispatcher.forward(request, response);
				}
				requestInterupted = true;
			}

		}
		if (httpServletRequest.getSession()
				.getAttribute("authentifiedPersonne") != null) {
			authentified = true;
		}
		if (!requestInterupted) {
			if (!authentified && !authentifying && resourceToScan) {
				
				String requestParam = httpServletRequest.getQueryString()!=null?"?".concat(httpServletRequest.getQueryString().toString()):"";
				httpServletRequest.getSession().setAttribute("originURL",
						httpServletRequest.getRequestURL().toString().concat(requestParam));
				httpServletResponse.sendRedirect("./TP13_Controller");
			} else {
				chain.doFilter(request, response);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
