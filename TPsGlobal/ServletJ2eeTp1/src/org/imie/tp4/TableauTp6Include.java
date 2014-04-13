package org.imie.tp4;

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

/**
 * Servlet implementation class TableauTp6
 */
@WebServlet("/TableauTp6Include")
public class TableauTp6Include extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TableauTp6Include() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Personne> personList = new ArrayList<Personne>();

		personList.clear();
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
		printWriter.println("<th>id</th><th>Nom</th><th>Prenom</th><th>Date de naissance</th>");
		printWriter.println("</tr></thead>");
		//Remplissage du tableau-----------------------------
		printWriter.println("<tbody>");
		for(Personne personne: personList){
			printWriter.println("<tr>");
			printWriter.println("<td>".concat(personne.getId().toString()).concat("</td>"));
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
