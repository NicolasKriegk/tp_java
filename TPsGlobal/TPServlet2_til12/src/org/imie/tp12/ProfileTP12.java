package org.imie.tp12;

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
@WebServlet("/profileTP12")
public class ProfileTP12 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileTP12() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		Personne personne = (Personne) request
				.getAttribute("profile");		
//		PrintWriter printWriter = response.getWriter();
		response.setCharacterEncoding("utf-8");
		buildIHM(response.getWriter(), personne);

	}

	private void buildIHM(PrintWriter printWriter, Personne personne) {
		buildHtmlForewords(printWriter);
		
		personUpdate(printWriter, personne);
//		printWriter.println("<hr>");
//		personSearchResultTable(printWriter, personnes);
		
		buildHtmlEnd(printWriter);
	}

	private void personUpdate(PrintWriter printWriter,
			Personne personne) {

		printWriter.println("<div><h2>Profil à modifier</h2>");
		printWriter.println("<form method=\"POST\" action=\"./TP12\">");
		printWriter.println("<label for=\"nom\">Nom: </label><input type=\"text\" name=\"nom\" value=\""
				.concat(personne.getNom())
				.concat("\"></br>"));
		printWriter.println("<label for=\"prenom\">Prenom: </label><input type=\"text\" name=\"prenom\" value=\""
				.concat(personne.getPrenom())
				.concat("\"></br>"));
		printWriter.println("<label for=\"dateNaissance\">Date de naissance: </label><input type=\"date\" name=\"dateNaissance\" value=\""
				.concat(String.valueOf(personne.getDateNaiss()))
				.concat("\"></br>"));
		printWriter.println("<label for=\"promotion\">Promotion: </label><input type=\"text\" name=\"promotion\" value=\""
				.concat(String.valueOf(personne.getPromotion().getLibelle()))
				.concat("\"></br>"));
		printWriter.println("<label for=\"password\">Mot de passe: </label><input type=\"text\" name=\"password\"></br>");
		printWriter.println("<input type=\"hidden\" name=\"idPersonne\" value=\""
				.concat(String.valueOf(personne.getId()))
				.concat("\" />"));
		printWriter.println("<input type=\"hidden\" name=\"profile\" value=\"update\"/>");
		printWriter.println("<input type=\"submit\" name=\"OK\" value=\"OK\"/>");
		printWriter.println("</form></div>");

	}

	private void buildHtmlEnd(PrintWriter printWriter) {
		printWriter.println("</body>");
		printWriter.println("</html>");
	}

	private void buildHtmlForewords(PrintWriter printWriter) {
		printWriter.println("<!DOCTYPE html>");
		printWriter.println("<html>");
		printWriter.println("<head>");
		printWriter
				.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		printWriter.println("</head>");
		printWriter.println("<body>");
	}

}
