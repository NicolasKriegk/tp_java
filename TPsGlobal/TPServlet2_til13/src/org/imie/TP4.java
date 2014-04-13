package org.imie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.imie.interfaceDAO.IPersonneDAO;
import org.imie.transverse.factory.AbstractFactory;
import org.imie.transverse.factory.ConcreteFactory;

/**
 * Servlet implementation class HelloWorld
 */
@WebServlet(
		urlPatterns = { "/TP4" }, 
		initParams = { 
				@WebInitParam(name = "design", value = "vert")
		})
public class TP4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TP4() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter printWriter= response.getWriter();
		response.setCharacterEncoding("UTF8");
		printWriter.println("<!DOCTYPE html>");
		printWriter.println("<html>");
		printWriter.println("<head>");
		printWriter.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		printWriter.println("<link rel=stylesheet type=\"text/css\" href=\"./".concat(getServletConfig().getInitParameter("design")).concat(".css\">"));
		printWriter.println("</head>");
		printWriter.println("<body>");
		printWriter.write("HELLO <span class=\"cursus\">".concat(request.getParameter("cursus")).concat("</span> !"));
		printWriter.println("</body>");
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
