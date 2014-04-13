package org.imie.tp11;

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

import org.imie.interfaceDAO.IPersonneDAO;
import org.imie.transverse.DTO.Personne;
import org.imie.transverse.DTO.Promotion;
import org.imie.transverse.factory.AbstractFactory;
import org.imie.transverse.factory.ConcreteSingletonFactory;

/**
 * Servlet implementation class TP10
 */
@WebServlet("/TP11")
public class TP11 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AbstractFactory factory = new ConcreteSingletonFactory();
	IPersonneDAO dao = factory.createPersonneDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TP11() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Personne> personnes = dao.rechercherPersonne(new Personne());

		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/affichTP11");
		request.setAttribute("personnes",personnes);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Personne personneQuery = new Personne(); 
		List<Personne> personnes = new ArrayList<Personne>();
		
		if(request.getParameter("nom") != null){
			personneQuery.setNom(String.valueOf(request.getParameter("nom")));
		}
		personnes = dao.rechercherPersonne(personneQuery);

		String idParam = request.getParameter("idPersonne");
		if (idParam != null) {
			Integer id = null;
			id = Integer.valueOf(idParam);
			if (id != null) {
				System.out.println("ID cliquée : ".concat(String.valueOf(id)));
				personneQuery = null;
				for (Personne personne : personnes) {
					if (personne.getId().equals(id)) {
						personneQuery = personne;
						personnes.clear();
						break;
					}
				}
				System.out.println(personneQuery.getNom());
			}
		}
		personnes = dao.rechercherPersonne(personneQuery);

		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/affichTP11");
		request.setAttribute("personnes",personnes);
		rd.forward(request, response);
	}

	/**
	 * @param printWriter
	 * @param personnes
	 */
	/**
	 * @param personnes
	 */
//	private List<Personne> initListPersonnes() {
//		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXX");
//		List<Personne> personnes = new ArrayList<Personne>();
//		Promotion promotion = new Promotion();
//		promotion.setLibelle("CDI09");
//
//		Personne personne1 = new Personne();
//		personne1.setId(15234);
//		personne1.setNom("toto");
//		personne1.setPrenom("toto");
//		personne1.setDateNaiss(new Date());
//		personne1.setPromotion(promotion);
//		personnes.add(personne1);
//
//		Personne personne2 = new Personne();
//		personne2.setId(657546);
//		personne2.setNom("tata");
//		personne2.setPrenom("tata");
//		personne2.setDateNaiss(new Date());
//		personne2.setPromotion(promotion);
//		personnes.add(personne2);
//		return personnes;
//	}

}
