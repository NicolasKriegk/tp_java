package org.imie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.imie.interfaceservice.IGestionEcoleService;
import org.imie.transverse.DTO.Personne;
import org.imie.transverse.DTO.Promotion;
import org.imie.transverse.factory.AbstractFactory;
import org.imie.transverse.factory.ConcreteSingletonFactory;

/**
 * Servlet implementation class TP10_COntroller
 */
@WebServlet("/TP10_Controller")
public class TP10_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AbstractFactory factory =  ConcreteSingletonFactory.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TP10_Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		
		IGestionEcoleService service = factory.creategGestionEcoleService();
		List<Personne> personnes = service.rechercherPersonne(new Personne());
		
		//session.setAttribute("personnes", personnes);
		request.setAttribute("personnes", personnes);
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/TP10_View");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter printWriter = response.getWriter();

		HttpSession session = request.getSession();

		IGestionEcoleService service = factory.creategGestionEcoleService();
		List<Personne> personnes = service.rechercherPersonne(new Personne());
		
//		@SuppressWarnings("unchecked")
//		List<Personne> personnes = (List<Personne>) session
//				.getAttribute("personnes");

//		String ligneParam = request.getParameter("ligne");
//		if (ligneParam != null) {
//			Integer ligne = null;
//			ligne = Integer.valueOf(ligneParam);
//			if (ligne != null) {
//				System.out.println("numero de ligne cliquée : ".concat(String
//						.valueOf(ligne)));
//				Personne personneSelected = personnes.get(ligne - 1);
//				System.out.println(personneSelected.getNom());
//			}
//		}

		String idParam = request.getParameter("idPersonne");
		if (idParam != null) {
			Integer id = null;
			id = Integer.valueOf(idParam);
			if (id != null) {
				System.out.println("ID cliquée : ".concat(String.valueOf(id)));
				Personne personneSelected = new Personne();
				personneSelected.setId(id);
				personneSelected = service.rechercherPersonne(personneSelected).get(0);
//				Personne personneSelected = null;
//				for (Personne personne : personnes) {
//					if (personne.getId().equals(id)) {
//						personneSelected = personne;
//						break;
//					}
//				}
				System.out.println(personneSelected.getNom());
			}
		}
		request.setAttribute("personnes", personnes);
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/TP10_View");
		dispatcher.forward(request, response);
		System.out.println("CONTROLLER");

	}

	/**
	 * @return
	 */
	private List<Personne> initListPersonnes() {
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXX");
		List<Personne> personnes = new ArrayList<Personne>();
		Promotion promotion = new Promotion();
		promotion.setLibelle("CDI09");

		Personne personne1 = new Personne();
		personne1.setId(15234);
		personne1.setNom("toto");
		personne1.setPrenom("toto");
		personne1.setDateNaiss(new Date());
		personne1.setPromotion(promotion);
		personnes.add(personne1);

		Personne personne2 = new Personne();
		personne2.setId(657546);
		personne2.setNom("tata");
		personne2.setPrenom("tata");
		personne2.setDateNaiss(new Date());
		personne2.setPromotion(promotion);
		personnes.add(personne2);
		return personnes;
	}
}
