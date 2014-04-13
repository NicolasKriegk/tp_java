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
@WebServlet("/TP11_Controller")
public class TP11_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AbstractFactory factory = ConcreteSingletonFactory.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TP11_Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		IGestionEcoleService service = factory.creategGestionEcoleService();
		List<Personne> personnes = service.rechercherPersonne(new Personne());
		request.setAttribute("personnes", personnes);
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/TP11_View");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter printWriter = response.getWriter();

		Personne searchPersonne = new Personne();
		String rechercheNom = request.getParameter("nom");
		String recherchePrenom = request.getParameter("prenom");
		;
		searchPersonne.setNom(rechercheNom);
		searchPersonne.setPrenom(recherchePrenom);

		IGestionEcoleService service = factory.creategGestionEcoleService();
		List<Personne> personnes = service.rechercherPersonne(searchPersonne);

		String idParam = request.getParameter("idPersonne");
		if (idParam != null) {
			Integer id = null;
			id = Integer.valueOf(idParam);
			if (id != null) {
				System.out.println("ID cliquée : ".concat(String.valueOf(id)));
				Personne personneSelected = new Personne();
				personneSelected.setId(id);
//				personneSelected = service.rechercherPersonne(personneSelected)
//						.get(0);
//				request.getSession().setAttribute("personneSelected",
//						personneSelected);
				response.sendRedirect("./TP12_Controller?personneId="
						.concat(String.valueOf(id)));
			}
		}

		if (request.getParameter("rechercher") != null) {
			request.setAttribute("personnes", personnes);
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/TP11_View");
			dispatcher.forward(request, response);
		}

	}

}
