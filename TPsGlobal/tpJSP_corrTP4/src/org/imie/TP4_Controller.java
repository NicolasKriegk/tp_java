package org.imie;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.imie.interfaceservice.IGestionEcoleService;
import org.imie.transverse.DTO.Personne;
import org.imie.transverse.factory.ConcreteSingletonFactory;

/**
 * Servlet implementation class TP3_Controller
 */
@WebServlet("/TP4_Controller/*")
public class TP4_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TP4_Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Pattern pattern = Pattern.compile(".*/TP4_Controller/([0-9]*)");
		Matcher matcher = pattern.matcher(request.getRequestURL());
		IGestionEcoleService gestionEcoleService = ConcreteSingletonFactory
				.getInstance().creategGestionEcoleService();
		Personne searchPersonne = new Personne();
		if (matcher.find()) {
			try {
				searchPersonne.setId(Integer.valueOf(matcher.group(1)));
				List<Personne> findedPersonnes = gestionEcoleService
						.rechercherPersonne(searchPersonne);
				if (findedPersonnes.size() > 0) {
					request.setAttribute("selectedPersonne",
							findedPersonnes.get(0));
					request.getRequestDispatcher("/WEB-INF/TP3.jsp").forward(
							request, response);
				} else {
					response.setStatus(404);
				}

			} catch (NumberFormatException e) {
				// url mal formée : on renvoie toutes les personnes
				List<Personne> foundPersonnes = gestionEcoleService.rechercherPersonne(searchPersonne);
				request.setAttribute("foundPersonnes",foundPersonnes);
				request.getRequestDispatcher("/WEB-INF/TP4.jsp").forward(
						request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
