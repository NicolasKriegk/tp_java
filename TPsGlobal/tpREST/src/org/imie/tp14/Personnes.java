package org.imie.tp14;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.imie.interfaceservice.IGestionEcoleService;
import org.imie.transverse.DTO.Personne;
import org.imie.transverse.factory.ConcreteSingletonFactory;


/**
 * Servlet implementation class Personnes
 */
@WebServlet("./Personnes")
public class Personnes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Personnes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IGestionEcoleService gestionEcoleService=  ConcreteSingletonFactory.getInstance().creategGestionEcoleService();
		Personne personne = new Personne();
		//preparation verification Id dans URI
		Pattern pattern = Pattern.compile(".*/Personnes/([0-9]*)");
		Matcher matcher = pattern.matcher(request.getRequestURI());
		System.out.println("adresse: ".concat(request.getRequestURI()));
		
		
		
		//recuperation des Id personnes dans l'URI, si existe
		if (matcher.find()) {
			personne.setId(Integer.valueOf(matcher.group(1)));
			System.out.println("adresse avec id ".concat(matcher.group(1)));
		}
		
		List<Personne> personnes = gestionEcoleService.rechercherPersonne(personne);
		request.setAttribute("personneList", personnes);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/PersonnesView");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
