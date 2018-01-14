package libriPackage;

import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class RimuoviLibroCatalogoServlet
 */


public class RimuoviLibroCatalogo extends HttpServlet {
	private static final long serialVersionUID = 1L;


	static LibriManager model = new LibriManager();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
	public RimuoviLibroCatalogo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Metodo per rimuovere un libro al catalogo.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
	    String messaggio = null;
	  
		
		try{
	   
	    if (action != null) {
	     if (action.equalsIgnoreCase("delete")) {
		    String id = request.getParameter("id");
			model.doRimuoviLibro(id);
	     } 
					{  
				    messaggio= "Libro rimosso con successo";
					request.setAttribute("messaggio", messaggio);
			         
			            
					}
				
		 
	  }
	    
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
	}
		
		String sort = request.getParameter("sort");

		try {
			request.removeAttribute("libri");
			request.setAttribute("libri", model.doRetrieveAll(sort));
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
	

	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Catalogo.jsp");
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
