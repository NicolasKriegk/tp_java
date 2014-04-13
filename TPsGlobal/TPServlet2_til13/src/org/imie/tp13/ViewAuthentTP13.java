package org.imie.tp13;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.imie.interfaceservice.IGestionEcoleService;
import org.imie.transverse.DTO.Personne;
import org.imie.transverse.DTO.Promotion;
import org.imie.transverse.factory.ConcreteSingletonFactory;

/**
 * Servlet implementation class ViewAuthentTP13
 */
@WebServlet("/ViewAuthentTP13")
public class ViewAuthentTP13 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAuthentTP13() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// inclusion de l'entete HTML
		String url = request.getRequestURL().toString();
		System.out.println(url);

		ServletContext context= request.getSession().getServletContext();
		RequestDispatcher rd= context.getRequestDispatcher("/HTMLHead");
		rd.include(request, response);
		
		buildIHM(response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private void buildIHM(PrintWriter printWriter) {

		printWriter.println("<body>");
		printWriter.println("<form method=\"POST\">");
		printWriter.println("<div id=\"formPersonne\">");

		printWriter.println("<div class=\"row\">");

		printWriter.println("<div class=\"cell\">");
		printWriter.println("<label>Login</label>");
		printWriter.println("</div>");
		printWriter.println("<div class=\"cell\">");
		printWriter.println("<input type=\"text\" name=\"inputNom\">");
		printWriter.println("</div>");

		printWriter.println("</div>");
		printWriter.println("<div class=\"row\">");

		printWriter.println("<div class=\"cell\">");
		printWriter.println("<label for=\"\">password</label>");
		printWriter.println("</div>");
		printWriter.println("<div class=\"cell\">");
		printWriter.println("<input type=\"password\" name=\"inputPassword\">");
		printWriter.println("</div>");

		printWriter.println("</div>");

		printWriter.println("</div>");
		printWriter
				.println("<input type=\"submit\" name=\"logAction\" value=\"in\"/>");
		printWriter
			.println("<input type=\"submit\" name=\"logAction\" value=\"out\"/>");
		printWriter.println("</form>");
//		printWriter.println("<a href=\"./TP11_Controller\">retour liste</a>");
		printWriter.println("</body>");
		printWriter.println("</html>");

	}
}
