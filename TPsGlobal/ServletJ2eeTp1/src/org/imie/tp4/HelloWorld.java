package org.imie.tp4;

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
				@WebInitParam(name = "design", value = "vert")
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

//	/* (non-Javadoc)
//	 * @see javax.servlet.GenericServlet#init()
//	 */
//	@Override
//	public void init() throws ServletException {
//		// TODO Auto-generated method stub
//	}
//
//	/* (non-Javadoc)
//	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
//	 */
//	@Override
//	public void init(ServletConfig config) throws ServletException {
//		// TODO Auto-generated method stub
//		super.init(config);
//
//	}

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
		printWriter.println("<link rel=\"stylesheet\" media=\"screen\" type=\"text/css\" title=\"style\" href=\""
					.concat(getServletConfig().getInitParameter("design"))
					.concat(".css\"/>"));
		printWriter.println("</head>");
		printWriter.println("<body>");
		printWriter.println("<p class=\"designColor\">Hello World! ".concat(request.getParameter("name")).concat("</p>"));
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
