package prestitiPackage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import libriPackage.LibriManager;

/**
 * Servlet implementation class ChiusuraPrestitoServlet
 */

public class ChiusuraPrestitoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	static PrestitiManager model = new PrestitiManager();
	static LibriManager model1 = new LibriManager();
	 
	/**
     * @see HttpServlet#HttpServlet()
     */
	public ChiusuraPrestitoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Metodo per chiudere un prestito attivo.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");

		try {
			if (action != null) {
				if (action.equalsIgnoreCase("Chiudi")) {
					int id = Integer.parseInt(request.getParameter("id"));
					model.doChiudi(id);	
				}
				
			}
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		
	
		try {
			request.removeAttribute("libri");
			request.setAttribute("libri", model.doTrovaPrestiti("cod_pr"));
		
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PrestitiAdmin.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
