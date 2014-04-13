package org.imie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.imie.transverse.DTO.Personne;

/**
 * Servlet implementation class TP10_View
 */
@WebServlet("/TP11_View")
public class TP11_View extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TP11_View() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		@SuppressWarnings("unchecked")
		List<Personne> personnes = (List<Personne>) request
				.getAttribute("personnes");
		buildIHM(request, response, personnes);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		List<Personne> personnes = (List<Personne>) request
				.getAttribute("personnes");
		buildIHM(request, response, personnes);
	}

	/**
	 * @param printWriter
	 * @param personnes
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void buildIHM(HttpServletRequest request,HttpServletResponse response, List<Personne> personnes) throws ServletException, IOException {
		PrintWriter printWriter = response.getWriter();
		printWriter.println("<!DOCTYPE html>");
		printWriter.println("<html>");
		printWriter.println("<head>");
		printWriter
				.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		printWriter
				.println("<link rel=stylesheet type=\"text/css\" href=\"./TP12.css\">");
		printWriter.println("</head>");
		printWriter.println("<body>");
		//inclusion du header
		RequestDispatcher dispatcher= request.getRequestDispatcher("TP13_header");
		dispatcher.include(request, response);

		printWriter.println("<form method=\"POST\">");
		printWriter.println("<input type=\"text\" name=\"nom\">");
		printWriter.println("<input type=\"text\" name=\"prenom\">");
		printWriter
				.println("<input type=\"submit\" name=\"rechercher\" value=\"rechercher\">");
		printWriter.println("</form>");
		printWriter
				.println("<table id=\"tablePersonne\"><thead><tr><th>nom</th><th>prenom</th><th>date naissance</th><th>promotion</th><th></th></tr></thead><tbody>");
		Integer cpt = 1;
		if (personnes != null) {
			for (Personne personne : personnes) {
				printWriter
						.println("<tr><td>"
								.concat(personne.getNom())
								.concat("</td><td>")
								.concat(personne.getPrenom())
								.concat("</td><td>")
								.concat(personne.getDateNaiss() != null ? personne
										.getDateNaiss().toString() : "")
								.concat("</td><td>")
								.concat(personne.getPromotion() != null ? personne
										.getPromotion().getLibelle() : "")
								.concat("</td><td>")
								.concat("<a href=\"./TP12_Controller?personneId="+personne.getId()+"\"> selection </a>")
//								.concat("<form method=\"POST\">")
//								.concat("<input type=\"hidden\" name=\"idPersonne\" value=\""
//										.concat(String.valueOf(personne.getId()))
//										.concat("\" />"))
//								.concat("<input type=\"submit\" value=\"selection\"/>")
//								.concat("</form>")
								.concat("</td></tr>"));

			}
		}
		printWriter.println("</tbody></table>");

		printWriter.println("</body>");
		printWriter.println("</html>");
	}
}
