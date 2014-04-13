package org.imie.tp9;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.imie.transverse.DTO.Personne;

/**
 * Servlet implementation class HelloWorld
 */
@WebServlet(
		urlPatterns = { "/HelloWorldTp9" }, 
		initParams = { 
				@WebInitParam(name = "design", value = "rouge"),
		})
public class HelloWorldTp9 extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorldTp9() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		if("redirect".equals(request.getParameter("mode"))){
//			response.sendRedirect("./TableauTp9");
//		}
//		else if("forward".equals(request.getParameter("mode"))){
//			ServletContext context= request.getSession().getServletContext();
//			RequestDispatcher rd = context.getRequestDispatcher("/TableauTp9");
//			rd.forward(request, response);		}
//		else if("include".equals(request.getParameter("mode"))){
	
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
			ServletContext context= request.getSession().getServletContext();
			RequestDispatcher rd= context.getRequestDispatcher("/TableauTp9Include");
			rd.include(request, response);
			//----------------------------------------
			printWriter.println("</body>");
			printWriter.println("</html>");
		
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
