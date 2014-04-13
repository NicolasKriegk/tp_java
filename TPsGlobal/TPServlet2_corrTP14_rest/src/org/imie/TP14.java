package org.imie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.imie.transverse.DTO.Personne;
import org.imie.transverse.factory.ConcreteSingletonFactory;

/**
 * Servlet implementation class TP14
 */
@WebServlet("/TP14/*")
public class TP14 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TP14() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Pattern pattern = Pattern.compile(".*/TP14/([0-9]*)");
		Matcher matcher = pattern.matcher(request.getRequestURL());
		Personne searchPersonne = new Personne();
		if (matcher.find()) {
			try {
				searchPersonne.setId(Integer.valueOf(matcher.group(1)));
			} catch (NumberFormatException e) {
				// url mal formée
			}
		}

		List<Personne> personnes = ConcreteSingletonFactory.getInstance()
				.creategGestionEcoleService()
				.rechercherPersonne(searchPersonne);
		PrintWriter printWriter = response.getWriter();
		for (Personne personne : personnes) {
			printWriter.println(String.valueOf(personne.getId()).concat(" - ").concat(
					personne.getNom()));
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doDelete(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Pattern pattern = Pattern.compile(".*/TP14/([0-9]*)");
		Matcher matcher = pattern.matcher(req.getRequestURL());
		Personne searchPersonne = new Personne();
		if (matcher.find()) {
			try {
				searchPersonne.setId(Integer.valueOf(matcher.group(1)));
				ConcreteSingletonFactory.getInstance()
						.creategGestionEcoleService()
						.deletePersonne(searchPersonne);
			} catch (NumberFormatException e) {
				// url mal formée
			}
		}

	}

	
}
