package org.imie.tp9;

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

/**
 * Servlet implementation class TableauTp9
 */
@WebServlet("/TableauTp9Include")
public class TableauTp9Include extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TableauTp9Include() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
//		IPersonneDAO personneNull = new fact();
//		personList = personneNull.rechercherPersonne(new Personne());

		@SuppressWarnings("unchecked")
		List<Personne> personnes = (List<Personne>) session.getAttribute("personnes");

		if(session.getAttribute("personnes") == null){
			System.out.println("---------------------------------------");
			List<Personne> personList = new ArrayList<Personne>();
			for(Integer i=0;i<10;i++){
				Personne personne = new Personne();
				personne.setId(i*20);
				personne.setNom(i.toString());
				personne.setPrenom(i.toString());
				personne.setDateNaiss(new Date());
				personList.add(personne);
			}
				personnes = personList; 
			session.setAttribute("personnes", personnes);
		}

		Personne personneSelected = new Personne();
		
//		if(request.getParameter("ligne") != null){
		if(request.getParameter("ligne") != null){
			System.out.println("ligne " + request.getParameter("ligne"));
			personneSelected = personnes.get(Integer.valueOf(request.getParameter("ligne")));
			System.out.println("Nom: ".concat(personneSelected.getNom()));
		}
		if(request.getParameter("id") != null){
			System.out.println("id " + request.getParameter("id"));
		}
		
		PrintWriter printWriter = response.getWriter();
		//Creation et sauvegarde du tableau en session pour reutilisation du meme apres clic
//		if(httpSession.getAttribute("tableau") == null){
		if(session.getAttribute("tableau") == null){
//		if(request.getParameter("ligne") == null){
			//Presentation du tableau
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
			String tableauWriter = "";
			tableauWriter =tableauWriter.concat("<table>");
			tableauWriter =tableauWriter.concat("<thead><tr>");
			tableauWriter =tableauWriter.concat("<th>Nom</th><th>Prenom</th><th>Date de naissance</th><th>Id</th>");
			tableauWriter =tableauWriter.concat("</tr></thead>");
			//Remplissage du tableau-----------------------------
			tableauWriter =tableauWriter.concat("<tbody>");
			Integer index = 0;
			for(Personne personne: personnes){
				index++;
				tableauWriter =tableauWriter.concat("<tr>");
				tableauWriter =tableauWriter.concat("<td>".concat(personne.getNom()).concat("</td>"));
				tableauWriter =tableauWriter.concat("<td>".concat(personne.getPrenom()).concat("</td>"));
				tableauWriter =tableauWriter.concat("<td>".concat(personne.getDateNaiss().toString()).concat("</td>"));
				tableauWriter =tableauWriter.concat("<td>")
						.concat("<form method=\"POST\" action=\"./HelloWorldTp9\">")
						.concat("<input type=\"hidden\" name=\"ligne=")
							.concat(String.valueOf(index+1))
							.concat("\" />")
						.concat("<input type=\"hidden\" name=\"id=")
							.concat(String.valueOf(personne.getId()))
							.concat("\" />")
						.concat("<input type=\"submit\" value=\"selection=")
							.concat("\" />")
						.concat("</form>")
						.concat("</td>")
						;
				tableauWriter =tableauWriter.concat("</tr>");
			}
			tableauWriter =tableauWriter.concat("</tbody>");
			tableauWriter =tableauWriter.concat("</table>");
			session.setAttribute("tableau",tableauWriter);
		}
		printWriter.println(session.getAttribute("tableau"));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
