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

import org.imie.transverse.DTO.Personne;
import org.imie.transverse.DTO.Promotion;

/**
 * Servlet implementation class HelloWorld
 */
@WebServlet(urlPatterns = { "/TP5" })
public class TP5 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TP5() {
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

		List<Personne> personnes = new ArrayList<Personne>();

		Promotion promotion = new Promotion();
		promotion.setLibelle("CDI09");

		Personne personne1 = new Personne();
		personne1.setNom("toto");
		personne1.setPrenom("toto");
		personne1.setDateNaiss(new Date());
		personne1.setPromotion(promotion);
		personnes.add(personne1);

		Personne personne2 = new Personne();
		personne2.setNom("tata");
		personne2.setPrenom("tata");
		personne2.setDateNaiss(new Date());
		personne2.setPromotion(promotion);
		personnes.add(personne2);
		
		printWriter.println("<!DOCTYPE html>");
		printWriter.println("<html>");
		printWriter.println("<head>");
		printWriter
				.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		printWriter.println("</head>");
		printWriter.println("<body>");
		printWriter
				.println("<table><thead><tr><th>nom</th><th>prenom</th><th>date naissance</th><th>promotion</th></tr></thead><tbody>");
		for (Personne personne : personnes) {
			printWriter.println("<tr><td>"
					.concat(personne.getNom())
					.concat("</td><td>")
					.concat(personne.getPrenom())
					.concat("</td><td>")
					.concat(personne.getDateNaiss().toString())
					.concat("</td><td>")
					.concat(personne.getPromotion() != null ? personne
							.getPromotion().getLibelle() : "")
					.concat("</td></tr>"));

		}
		printWriter.println("</tbody></table>");

		printWriter.println("</body>");
		printWriter.println("</html>");

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
