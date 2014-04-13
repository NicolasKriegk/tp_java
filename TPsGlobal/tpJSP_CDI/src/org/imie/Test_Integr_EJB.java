package org.imie;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.imie.interfaceservice.IGestionEcoleService;
import org.imie.transverse.DTO.Personne;

/**
 * Servlet implementation class Test_Integr_CDI
 */
@WebServlet("/Test_Integr_EJB")
public class Test_Integr_EJB extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//en CDI
//	@Inject @TransactionBeanJDBC(TransactionModeJDBC.THREADSAFE) IGestionEcoleService gestionEcoleService; 
	//en EJB
//	@EJB(beanName="GestionEcoleService") private IGestionEcoleService gestionEcoleService;
	//en EJB par JNDI
//	@EJB(lookup="java:comp/env/ejb/GestionEcoleService") private IGestionEcoleService gestionEcoleService;
	@EJB(lookup="java:module/GestionEcoleService") private IGestionEcoleService gestionEcoleService;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test_Integr_EJB() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Personne> personnes = gestionEcoleService.rechercherPersonne(new Personne());
		System.out.println(personnes.get(0).getPrenom());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
