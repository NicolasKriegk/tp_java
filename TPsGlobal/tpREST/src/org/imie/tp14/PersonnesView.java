package org.imie.tp14;

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
 * Servlet implementation class PersonnesView
 */
@WebServlet("/PersonnesView")
public class PersonnesView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonnesView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter printWriter = response.getWriter();
		@SuppressWarnings("unchecked")
		List<Personne> personnes = (List<Personne>) request.getAttribute("personneList");
		printWriter.println("<!DOCTYPE html>");
		printWriter.println("<html>");
		printWriter.println("<head>");
		printWriter.println("</head>");
		printWriter.println("<body>");
		for(Personne personne : personnes){
			printWriter.println("<span>Id: ".concat(String.valueOf(personne.getId())).concat("</span></br>"));
			printWriter.println("<span>Nom: ".concat(String.valueOf(personne.getNom())).concat("</span></br>"));
			printWriter.println("<span>Prenom: ".concat(String.valueOf(personne.getPrenom())).concat("</span></br>"));
			printWriter.println("<hr>");
		}
		printWriter.println("</body>");
		printWriter.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
