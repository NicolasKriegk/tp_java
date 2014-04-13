package org.imie.tp7;

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
 * Servlet implementation class TableauTp7
 */
@WebServlet("/TableauTp7Include")
public class TableauTp7Include extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TableauTp7Include() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Personne> personList = new ArrayList<Personne>();

		personList.clear();
//		IPersonneDAO personneNull = new fact();
//		personList = personneNull.rechercherPersonne(new Personne());
		for(Integer i=0;i<10;i++){
			Personne personne = new Personne();
			personne.setId(i);
			personne.setNom(i.toString());
			personne.setPrenom(i.toString());
			personne.setDateNaiss(new Date());
			personList.add(personne);
		}

			//Presentation du tableau
		PrintWriter printWriter = response.getWriter();
		printWriter.println("<table>");
		printWriter.println("<thead><tr>");
		printWriter.println("<th>N°</br>(cliquable)</th><th>Nom</th><th>Prenom</th><th>Date de naissance</th>");
		printWriter.println("</tr></thead>");
			//Remplissage du tableau-----------------------------
		printWriter.println("<tbody>");
			Integer index = 0;
			for(Personne personne: personList){
				index++;
				printWriter.println("<tr>");
	//			printWriter.println("<td>".concat(personne.getId().toString()).concat("</td>"));
				printWriter.println("<td><a id=\"T1L".concat(index.toString())
						.concat("\" href=\"http://localhost:8080/ServletJ2eeTp1/HelloWorldTp7?mode=include&ligne=")
						.concat(String.valueOf(index))
						.concat("\" >")
						.concat(String.valueOf(index))
						.concat("</a></td>")
						);
				printWriter.println("<td>".concat(personne.getNom()).concat("</td>"));
				printWriter.println("<td>".concat(personne.getPrenom()).concat("</td>"));
				printWriter.println("<td>".concat(personne.getDateNaiss().toString()).concat("</td>"));
				printWriter.println("</tr>");
			}
			printWriter.println("</tbody>");
			printWriter.println("</table>");

	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
