package org.imie.tp13;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class AuthentTP13
 */
@WebServlet("/AuthentTP13")
public class AuthentTP13 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthentTP13() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/ViewAuthentTP13");
		String url = request.getRequestURL().toString();
		System.out.println(url);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = request.getRequestURL().toString();
		System.out.println(url);

		String logAction = request.getParameter("logAction");
		System.out.println(logAction);
		if (!logAction.isEmpty() && logAction != null){
//			String identified = null;
			Personne personne = new Personne();
			if("in".equals(logAction)){
				IGestionEcoleService gestionEcoleService=  ConcreteSingletonFactory.getInstance().creategGestionEcoleService();
				personne.setNom(request.getParameter("nom"));
				personne.setPassw(request.getParameter("password"));
				List<Personne> personnes = gestionEcoleService.rechercherPersonne(personne);
				if(personnes.size()==1){
					request.getSession().setAttribute("identified", personnes.get(0));
				}
				response.sendRedirect("/TP11_Controller");
			}
			else{
				response.sendRedirect("/AuthentTP13");
			}
		}
	}
			
			

}
