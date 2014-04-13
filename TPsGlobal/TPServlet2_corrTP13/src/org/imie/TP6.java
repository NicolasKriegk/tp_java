package org.imie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TP6
 */
@WebServlet(urlPatterns = { "/TP6" }, initParams = { @WebInitParam(name = "mode", value = "forward") })
public class TP6 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TP6() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String mode = getServletConfig().getInitParameter("mode");
		ServletContext context = request.getSession().getServletContext();
		PrintWriter printWriter = response.getWriter();
		if ("redirect".equals(mode)) {
			response.sendRedirect("TP5");
		} else if ("forward".equals(mode)) {
			
			RequestDispatcher dispatcher = context.getRequestDispatcher("/TP5");
			printWriter.println("<span>avant tableau</span>");
			dispatcher.forward(request, response);
			
			printWriter.println("<span>apres tableau</span>");
		}else if ("include".equals(mode)) {
			
			
			printWriter.println("<!DOCTYPE html>");
			printWriter.println("<html>");
			printWriter.println("<head>");
			printWriter
					.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
			printWriter.println("</head>");
			printWriter.println("<body>");
			printWriter.println("<span>avant tableau</span>");
			
			RequestDispatcher dispatcher = context.getRequestDispatcher("/TP6_1");
			dispatcher.include(request, response);
			
			printWriter.println("<span>apres tableau</span>");
			printWriter.println("</body>");
			printWriter.println("</html>");
		}

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
