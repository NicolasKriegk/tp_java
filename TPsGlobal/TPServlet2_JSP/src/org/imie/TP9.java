package org.imie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.imie.transverse.DTO.Personne;
import org.imie.transverse.DTO.Promotion;

/**
 * Servlet implementation class TP8
 */
@WebServlet("/TP9")
public class TP9 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TP9() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter printWriter = response.getWriter();

		HttpSession session = request.getSession();

		@SuppressWarnings("unchecked")
		List<Personne> personnes = initListPersonnes();
		session.setAttribute("personnes", personnes);

		buildIHM(printWriter, personnes);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter printWriter = response.getWriter();

		HttpSession session = request.getSession();

		@SuppressWarnings("unchecked")
		List<Personne> personnes = (List<Personne>) session
				.getAttribute("personnes");

		String ligneParam = request.getParameter("ligne");
		if (ligneParam != null) {
			Integer ligne = null;
			ligne = Integer.valueOf(ligneParam);
			if (ligne != null) {
				System.out.println("numero de ligne cliquée : ".concat(String
						.valueOf(ligne)));
				Personne personneSelected = personnes.get(ligne - 1);
				System.out.println(personneSelected.getNom());
			}
		}

		String idParam = request.getParameter("idPersonne");
		if (idParam != null) {
			Integer id = null;
			id = Integer.valueOf(idParam);
			if (id != null) {
				System.out.println("ID cliquée : ".concat(String.valueOf(id)));
				Personne personneSelected = null;
				for (Personne personne : personnes) {
					if (personne.getId().equals(id)) {
						personneSelected = personne;
						break;
					}
				}
				System.out.println(personneSelected.getNom());
			}
		}

		buildIHM(printWriter, personnes);
	}

	/**
	 * @param printWriter
	 * @param personnes
	 */
	private void buildIHM(PrintWriter printWriter, List<Personne> personnes) {
		printWriter.println("<!DOCTYPE html>");
		printWriter.println("<html>");
		printWriter.println("<head>");
		printWriter
				.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		printWriter.println("</head>");
		printWriter.println("<body>");
		printWriter
				.println("<table><thead><tr><th>nom</th><th>prenom</th><th>date naissance</th><th>promotion</th></tr></thead><tbody>");
		Integer cpt = 1;
		for (Personne personne : personnes) {
			printWriter
					.println("<tr><td>"
							.concat(personne.getNom())
							.concat("</td><td>")
							.concat(personne.getPrenom())
							.concat("</td><td>")
							.concat(personne.getDateNaiss().toString())
							.concat("</td><td>")
							.concat(personne.getPromotion() != null ? personne
									.getPromotion().getLibelle() : "")
							.concat("</td><td><form method=\"POST\">")
							.concat("<input type=\"hidden\" name=\"idPersonne\" value=\""
									.concat(String.valueOf(personne.getId()))
									.concat("\" />"))
							.concat("<input type=\"hidden\" name=\"ligne\" value=\""
									.concat(String.valueOf(cpt++)).concat(
											"\" />"))
							.concat("<input type=\"submit\" value=\"selection\"/>")
							.concat("</form></td></tr>"));

		}
		printWriter.println("</tbody></table>");

		printWriter.println("</body>");
		printWriter.println("</html>");
	}

	/**
	 * @param personnes
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
