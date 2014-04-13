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

import org.imie.transverse.DTO.Personne;

/**
 * Servlet implementation class TableauTp6
 */
@WebServlet("/TableauTp6")
public class TableauTp7 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TableauTp7() {
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

		response.setCharacterEncoding("UTF8");
		PrintWriter printWriter = response.getWriter();
		printWriter.println("<!DOCTYPE html>");
		printWriter.println("<html>");
		printWriter.println("<head>");
		printWriter.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>");
		String design = getServletConfig().getInitParameter("design"); 
		if(design != null){
			printWriter.println("<link rel=\"stylesheet\" media=\"screen\" type=\"text/css\" title=\"style\" href=\""
						.concat(getServletConfig().getInitParameter("design"))
						.concat(".css\"/>"));
		}
		else{
			printWriter.println("<link rel=\"stylesheet\" media=\"screen\" type=\"text/css\" title=\"style\" href=\""
					.concat("rouge")
					.concat(".css\"/>"));
		}
		printWriter.println("</head>");
		printWriter.println("<body>");
		String paramName = request.getParameter("name"); 
		if(paramName != null){
			printWriter.println("<p class=\"designColor\">Hello World! ".concat(request.getParameter("name")).concat("</p>"));
		}
		else{
			printWriter.println("<p class=\"designColor\">Hello World!</p>");
		}
		printWriter.println("<br/>");
		printWriter.println("<br/>");
		//Presentation du tableau
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
		//----------------------------------------
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
