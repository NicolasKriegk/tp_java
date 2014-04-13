package org.imietest;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Personne;

import org.imie.service.GestionEcoleServiceJPALocal;


/**
 * Servlet implementation class TestJPA
 */
@WebServlet("/TestJPA")
public class TestJPA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @EJB GestionEcoleServiceJPALocal service;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public TestJPA() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Personne> personnes = service.rechercherPersonne(new Personne());
		System.out.println(personnes.size());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
