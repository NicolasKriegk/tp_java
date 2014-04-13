package org.imie;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.imie.transverse.DTO.Promotion;
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

		// a faire quelque soit le pattern
		IGestionEcoleService gestionEcoleService = ConcreteSingletonFactory
				.getInstance().creategGestionEcoleService();
		Personne searchPersonne = new Personne();
		request.setAttribute("promotions",
				gestionEcoleService.rechercherPromotion(new Promotion()));

		// declaration pattern create
		Pattern patternCreate = Pattern.compile(".*/TP4_Controller/create");
		Matcher matcherCreate = patternCreate.matcher(request.getRequestURL());
		// declaration pattern read
		Pattern patternRead = Pattern
				.compile(".*/TP4_Controller/read/([0-9]*)");
		Matcher matcherRead = patternRead.matcher(request.getRequestURL());
		// declaration pattern delete
		Pattern patternDelete = Pattern
				.compile(".*/TP4_Controller/delete/([0-9]*)");
		Matcher matcherDelete = patternDelete.matcher(request.getRequestURL());

		// url en create
		if (matcherCreate.find()) {
			request.getRequestDispatcher("/WEB-INF/TP3.jsp").forward(request,
					response);
		}
		//url de delete
		else if (matcherDelete.find()) {
			try {
				searchPersonne.setId(Integer.valueOf(matcherDelete.group(1)));
				List<Personne> foundPersonnes = gestionEcoleService
						.rechercherPersonne(searchPersonne);
				if (foundPersonnes.size() > 0) {
					gestionEcoleService.deletePersonne(foundPersonnes.get(0));

				} else {
					response.setStatus(404);
				}

			} catch (NumberFormatException e) {
				// url mal formée : pas de suppression
			}
			List<Personne> foundPersonnes = gestionEcoleService
					.rechercherPersonne(new Personne());
			request.setAttribute("foundPersonnes", foundPersonnes);
			request.getRequestDispatcher("/WEB-INF/TP4.jsp").forward(
					request, response);
		}
		// url en read
		else if (matcherRead.find()) {
			try {
				searchPersonne.setId(Integer.valueOf(matcherRead.group(1)));
				List<Personne> foundPersonnes = gestionEcoleService
						.rechercherPersonne(searchPersonne);
				if (foundPersonnes.size() > 0) {
					request.setAttribute("personneSelected",
							foundPersonnes.get(0));
					request.getRequestDispatcher("/WEB-INF/TP3.jsp").forward(
							request, response);
				} else {
					response.setStatus(404);
				}

			} catch (NumberFormatException e) {
				// url mal formée : on renvoie toutes les personnes
				List<Personne> foundPersonnes = gestionEcoleService
						.rechercherPersonne(searchPersonne);
				request.setAttribute("foundPersonnes", foundPersonnes);
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

		
		// construction du service
		IGestionEcoleService gestionEcoleService = ConcreteSingletonFactory
				.getInstance().creategGestionEcoleService();
		// recherche de la personne à modifier
		Personne updatedPerson = new Personne();

		updatedPerson = gestionEcoleService.rechercherPersonne(updatedPerson)
				.get(0);
		// affectation des nouvelles valeurs
		String inputNom = request.getParameter("inputNom");
		updatedPerson.setNom(inputNom);
		String inputPrenom = request.getParameter("inputPrenom");
		updatedPerson.setPrenom(inputPrenom);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String inputDateNaissString = request.getParameter("inputDateNaiss");
		try {
			Date inputDateNaiss = simpleDateFormat.parse(inputDateNaissString);
			updatedPerson.setDateNaiss(inputDateNaiss);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

		String inputPassword = request.getParameter("inputPassword");
		if (inputPassword != null && !inputPassword.isEmpty()) {
			updatedPerson.setPassw(inputPassword);
		}

		String inputPromotionString = request.getParameter("inputPromotion");
		if (!inputPromotionString.isEmpty()) {
			Integer inputPromotion = Integer.valueOf(inputPromotionString);
			Promotion searchPromotion = new Promotion();
			searchPromotion.setId(inputPromotion);
			searchPromotion = gestionEcoleService.rechercherPromotion(
					searchPromotion).get(0);
			updatedPerson.setPromotion(searchPromotion);
		} else {
			updatedPerson.setPromotion(null);
		}

		if (request.getParameter("create") != null) {
			gestionEcoleService.insertPersonne(updatedPerson);
		}

		if (request.getParameter("update") != null) {
			Integer inputId = Integer.valueOf(request.getParameter("inputId"));
			updatedPerson.setId(inputId);
			gestionEcoleService.updatePersonne(updatedPerson);
		}

		response.sendRedirect("/TPJSP/TP4_Controller/read/");
	}

}
