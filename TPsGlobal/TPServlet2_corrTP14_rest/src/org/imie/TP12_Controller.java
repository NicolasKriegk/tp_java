package org.imie;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
 * Servlet implementation class TP12_Controller
 */
@WebServlet("/TP12_Controller")
public class TP12_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TP12_Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		Personne personne = (Personne) request.getSession().getAttribute(
//				"personneSelected");
		String personneIdString = request.getParameter("personneId");
		if (personneIdString!=null && !personneIdString.isEmpty()){

			
			Integer personneId  = Integer.valueOf(personneIdString);
			IGestionEcoleService gestionEcoleService=  ConcreteSingletonFactory.getInstance().creategGestionEcoleService();
			Personne personne = new Personne();
			personne.setId(personneId);
			personne = gestionEcoleService.rechercherPersonne(personne).get(0);
			request.setAttribute("personneSelected", personne);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/TP12_View");
			dispatcher.forward(request, response);
			
		} else {
			response.sendRedirect("./TP11_Controller");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// recupération de la personne en base
		Integer inputId = Integer.valueOf(request.getParameter("inputId"));
		// construction du service
		IGestionEcoleService gestionEcoleService = ConcreteSingletonFactory
				.getInstance().creategGestionEcoleService();
		// recherche de la personne à modifier
		Personne updatedPerson = new Personne();
		updatedPerson.setId(inputId);
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
		if (!inputPromotionString.isEmpty()){
			Integer inputPromotion = Integer.valueOf(inputPromotionString);
			Promotion searchPromotion = new Promotion();
			searchPromotion.setId(inputPromotion);
			searchPromotion = gestionEcoleService.rechercherPromotion(searchPromotion).get(0);
			updatedPerson.setPromotion(searchPromotion);
		}else{
			updatedPerson.setPromotion(null);
		}
		

		// mise à jour
		updatedPerson = gestionEcoleService.updatePersonne(updatedPerson);

		request.setAttribute("personneSelected", updatedPerson);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/TP12_View");
		dispatcher.forward(request, response);

	}

}
