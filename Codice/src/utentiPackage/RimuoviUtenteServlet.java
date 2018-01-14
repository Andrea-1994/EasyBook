package utentiPackage;

import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class RimuoviUtenteServlet
 */


public class RimuoviUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	static UtentiManager model = new UtentiManager();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
	public RimuoviUtenteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Metodo per rimuovere un utente registrato.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
	    String messaggio = null;
	  
		
		try{
	   
	    if (action != null) {
	     if (action.equalsIgnoreCase("delete")) {
		    String id = request.getParameter("id");
			model.doDelete(id);
	     } 
					{  
				    messaggio= "Utente rimosso con successo";
					request.setAttribute("messaggio", messaggio);
			         
			            
					}
				
		 
	  }
	    
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
	}
		
		String sort = request.getParameter("sort");
		
		try {
			request.removeAttribute("utenti");
			request.setAttribute("utenti", model.doRetrieveAlluser(sort));
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}	
	
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ListaUtenti.jsp");
	dispatcher.forward(request, response);
		 
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
