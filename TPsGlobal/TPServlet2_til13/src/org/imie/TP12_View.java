package org.imie;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

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
 * Servlet implementation class TP12_View
 */
@WebServlet("/TP12_View")
public class TP12_View extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TP12_View() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Personne personneSelected = (Personne) request
				.getAttribute("personneSelected");
		buildIHM(response.getWriter(), personneSelected);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Personne personneSelected = (Personne) request
				.getAttribute("personneSelected");
		buildIHM(response.getWriter(), personneSelected);
	}

	private void buildIHM(PrintWriter printWriter, Personne personneSelected) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		printWriter.println("<!DOCTYPE html>");
		printWriter.println("<html>");
		printWriter.println("<head>");
		printWriter
				.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		printWriter.println("<link rel=stylesheet type=\"text/css\" href=\"./TP12.css\">");
		printWriter.println("</head>");
		printWriter.println("<body>");
		printWriter.println("<form method=\"POST\">");
		printWriter.println("<div id=\"formPersonne\">");

		printWriter.println("<div class=\"row\">");

		printWriter.println("<div class=\"cell\">");
		printWriter.println("<label for=\"\">nom</label>");
		printWriter.println("</div>");
		printWriter.println("<div class=\"cell\">");
		printWriter.println("<input type=\"text\" name=\"inputNom\" value=\""
				+ personneSelected.getNom() + "\">");
		printWriter.println("</div>");

		printWriter.println("</div>");
		printWriter.println("<div class=\"row\">");

		printWriter.println("<div  class=\"cell\">");
		printWriter.println("<label for=\"\">prenom</label>");
		printWriter.println("</div>");
		printWriter.println("<div class=\"cell\">");
		printWriter
				.println("<input type=\"text\" name=\"inputPrenom\" value=\""
						+ personneSelected.getPrenom() + "\">");
		printWriter.println("</div>");

		printWriter.println("</div>");
		printWriter.println("<div class=\"row\">");

		printWriter.println("<div class=\"cell\">");
		printWriter.println("<label for=\"\">Date naissance</label>");
		printWriter.println("</div>");
		printWriter.println("<div class=\"cell\">");
		printWriter
				.println("<input type=\"text\" name=\"inputDateNaiss\" value=\""
						+ simpleDateFormat.format(personneSelected
								.getDateNaiss()) + "\">");
		printWriter.println("</div>");

		printWriter.println("</div>");
		printWriter.println("<div>");

		printWriter.println("<div class=\"cell\">");
		printWriter.println("<label for=\"\">promotion</label>");
		printWriter.println("</div>");
		printWriter.println("<div class=\"cell\">");
		printWriter.println("<select name=\"inputPromotion\">");
		IGestionEcoleService gestionEcoleService = ConcreteSingletonFactory
				.getInstance().creategGestionEcoleService();
		List<Promotion> promotions = gestionEcoleService
				.rechercherPromotion(new Promotion());
		printWriter.println("<option value=\"\"></option>");
		for (Promotion promotion : promotions) {
			Boolean selected = false;
			if (personneSelected.getPromotion() != null
					&& personneSelected.getPromotion().getId()
							.equals(promotion.getId())) {
				selected = true;
			}
			printWriter.println("<option value=\"" + promotion.getId() + "\" "
					+ (selected ? "selected" : "") + ">"
					+ promotion.getLibelle() + "</option>");
		}
		printWriter.println("</select>");
		printWriter.println("</div>");

		printWriter.println("</div>");
		printWriter.println("<div class=\"row\">");

		printWriter.println("<div class=\"cell\">");
		printWriter.println("<label for=\"\">password</label>");
		printWriter.println("</div>");
		printWriter.println("<div class=\"cell\">");
		printWriter.println("<input type=\"password\" name=\"inputPassword\">");
		printWriter.println("</div>");

		printWriter.println("</div>");

		printWriter.println("</div>");
		printWriter.println("<input type=\"hidden\" name=\"inputId\" value=\""
				+ personneSelected.getId() + "\"/>");
		printWriter
				.println("<input type=\"submit\" name=\"valider\" value=\"Valider\"/>");
		printWriter.println("</form>");
		printWriter.println("<a href=\"./TP11_Controller\">retour liste</a>");
		printWriter.println("</body>");
		printWriter.println("</html>");

	}
}
