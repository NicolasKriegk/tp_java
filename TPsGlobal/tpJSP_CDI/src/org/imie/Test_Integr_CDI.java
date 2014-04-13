package org.imie;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.imie.interfaceservice.IGestionEcoleService;
import org.imie.qualifier.TransactionBeanJDBC;
import org.imie.qualifier.TransactionModeJDBC;
import org.imie.transverse.DTO.Personne;

/**
 * Servlet implementation class Test_Integr_CDI
 */
@WebServlet("/Test_Integr_CDI")
public class Test_Integr_CDI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Inject @TransactionBeanJDBC(TransactionModeJDBC.THREADSAFE) IGestionEcoleService gestionEcoleService; 
//	@Inject @TransactionBeanJDBC(TransactionModeJDBC.THREADSAFE) IPersonneDAO personneDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test_Integr_CDI() {
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
