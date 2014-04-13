package org.imie.tp11;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.imie.transverse.DTO.Personne;

/**
 * Servlet implementation class TP11affich
 */
@WebServlet("/affichTP11")
public class AffichTP11 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AffichTP11() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		List<Personne> personnes = (List<Personne>) request
				.getAttribute("personnes");		
//		PrintWriter printWriter = response.getWriter();
		buildIHM(response.getWriter(), personnes);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		List<Personne> personnes = (List<Personne>) request
				.getAttribute("personnes");		
//		PrintWriter printWriter = response.getWriter();
		buildIHM(response.getWriter(), personnes);
	}

	private void buildIHM(PrintWriter printWriter, List<Personne> personnes) {
		printWriter.println("<!DOCTYPE html>");
		printWriter.println("<html>");
		printWriter.println("<head>");
		printWriter
				.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		printWriter.println("</head>");
		printWriter.println("<body>");
		
		personSearchForm(printWriter);
		printWriter.println("<hr>");
		personSearchResultTable(printWriter, personnes);
		
		printWriter.println("</body>");
		printWriter.println("</html>");
	}

	private void personSearchResultTable(PrintWriter printWriter,
			List<Personne> personnes) {
		printWriter
				.println("<table><thead><tr><th>nom</th><th>prenom</th><th>date naissance</th><th>promotion</th></tr></thead><tbody>");
		Integer cpt = 1;
		for (Personne personne : personnes) {
			printWriter
					.println("<tr><td>"
							.concat(personne.getNom() != null ? personne
									.getNom() : "")
							.concat("</td><td>")
							.concat(personne.getPrenom() != null ? personne
									.getPrenom() : "")
							.concat("</td><td>")
							.concat(personne.getDateNaiss() != null ? personne
									.getDateNaiss().toString() : "")
							.concat("</td><td>")
							.concat(personne.getPromotion() != null ? personne
									.getPromotion().getLibelle() : "")
							.concat("</td><td><form method=\"POST\" action=\"./TP11\">")
							.concat("<input type=\"hidden\" name=\"idPersonne\" value=\""
									.concat(String.valueOf(personne.getId()))
									.concat("\" />"))
//							.concat("<input type=\"hidden\" name=\"ligne\" value=\""
//									.concat(String.valueOf(cpt++)).concat(
//											"\" />"))
							.concat("<input type=\"submit\" value=\"selection\"/>")
							.concat("</form></td></tr>"));

		}
		printWriter.println("</tbody></table>");
	}

	private void personSearchForm(PrintWriter printWriter) {
		printWriter.println("<div><h2>Recherche de personne dans la base</h2>");
		printWriter.println("<form method=\"POST\" action=\"./TP11\">");
		printWriter.println("<label for=\"nom\">Nom: </label><input type=\"text\" name=\"nom\">");
		printWriter.println("<input type=\"submit\" value=\"OK\"/>");
		printWriter.println("</form></div>");

	}

}
