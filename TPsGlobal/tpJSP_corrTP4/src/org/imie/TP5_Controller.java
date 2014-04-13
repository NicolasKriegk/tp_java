package org.imie;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.imie.interfaceservice.IGestionEcoleService;
import org.imie.transverse.DTO.Personne;
import org.imie.transverse.factory.AbstractFactory;
import org.imie.transverse.factory.ConcreteSingletonFactory;

/**
 * Servlet implementation class TP3_Controller
 */
@WebServlet("/TP5_Controller/*")
public class TP5_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AbstractFactory factory = ConcreteSingletonFactory.getInstance();
	IGestionEcoleService service = factory.creategGestionEcoleService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TP5_Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Pattern pattern = Pattern.compile(".*/TP5_Controller/([0-9]*)");
		Matcher matcher = pattern.matcher(request.getRequestURL());
		Personne searchPersonne = new Personne();
		if (matcher.find()) {
			try {
				searchPersonne.setId(Integer.valueOf(matcher.group(1)));
				List<Personne> findedPersonnes = service.rechercherPersonne(searchPersonne);
				if (findedPersonnes.size() > 0) {
					request.setAttribute("selectedPersonne",
							findedPersonnes.get(0));
					request.getRequestDispatcher("/WEB-INF/tp5/TP5_Profile.jsp").forward(
							request, response);
				} else {
					response.setStatus(404);
				}

			} catch (NumberFormatException e) {
				// url mal formée : on renvoie toutes les personnes
				List<Personne> foundPersonnes = service.rechercherPersonne(searchPersonne);
				request.setAttribute("foundPersonnes",foundPersonnes);
				request.getRequestDispatcher("/WEB-INF/tp5/TP5_Tab.jsp").forward(
						request, response);
			}
		}
		else{
			List<Personne> foundPersonnes = service.rechercherPersonne(searchPersonne);
			request.setAttribute("foundPersonnes",foundPersonnes);
			request.getRequestDispatcher("/WEB-INF/tp5/TP5_Tab.jsp").forward(
					request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Personne selectedPersonne = new Personne();
		List<Personne> personnes = null;
		if ("Rechercher".equals(request.getParameter("rechercher"))) {
			String rechercheNom = request.getParameter("nom");
			String recherchePrenom = request.getParameter("prenom");
			selectedPersonne.setNom(rechercheNom);
			selectedPersonne.setPrenom(recherchePrenom);
	
		}else if(request.getParameter("profileId") != null){
			Integer profileId = Integer.valueOf(request.getParameter("profileId"));
			selectedPersonne.setId(profileId);
		}
		personnes = service.rechercherPersonne(selectedPersonne);
		request.setAttribute("foundPersonnes", personnes);
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/tp5/TP5_Tab.jsp");
		dispatcher.forward(request, response);

	}

}
