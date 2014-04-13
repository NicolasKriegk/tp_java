package org.imie.tp3;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorld
 */
@WebServlet(
		urlPatterns = { "/HelloWorld" }, 
		initParams = { 
				@WebInitParam(name = "couleur", value = "rouge")
		})
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorld() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF8");
		PrintWriter printWriter = response.getWriter();
		printWriter.println("<!DOCTYPE html>");
		printWriter.println("<html>");
		printWriter.println("<head>");
		printWriter.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>");
		printWriter.println("</head>");
		printWriter.println("<body>");
		printWriter.println("<p>Hello World! ".concat(request.getParameter("name")).concat("</p>"));
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
