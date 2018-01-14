package prestitiPackage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class VisualizzaPrestitiServlet
 */
public class VisualizzaPrestitiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	static PrestitiManager model = new PrestitiManager();
	
	
	/**
     * @see HttpServlet#HttpServlet()
     */
	public VisualizzaPrestitiServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Metodo per visualizzare tutti i prestiti attivi e i prestiti dell'utente.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session.getAttribute("num_tessera") != null) {
		 if(session.getAttribute("num_tessera").equals("admin")) {

		try {
			request.removeAttribute("libri");
			request.setAttribute("libri", model.doTrovaPrestiti("cod_pr"));
		
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		
		 
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PrestitiAdmin.jsp");
		dispatcher.forward(request, response);
		 
		 
		}else {
		
       try {
			
			String id = (String)session.getAttribute("num_tessera");
			request.removeAttribute("libri");
			request.setAttribute("libri", model.doTrovaPrestito(id));
		
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		
		}
		}
		RequestDispatcher dispatcher1 = getServletContext().getRequestDispatcher("/PrestitiUtente.jsp");
		dispatcher1.forward(request, response);
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
