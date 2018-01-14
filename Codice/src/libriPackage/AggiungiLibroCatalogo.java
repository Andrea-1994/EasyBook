package libriPackage;

import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class AggiungiLibroCatalogoServlet
 */



public class AggiungiLibroCatalogo extends HttpServlet {
	private static final long serialVersionUID = 1L;


	static LibriManager model = new LibriManager();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
	public AggiungiLibroCatalogo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Metodo per aggiungere un libro al catalogo.
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
	    String messaggio = null;
	  
		
		try{
	   
	    if (action != null) {
	     if (action.equalsIgnoreCase("aggiungilibro")){
			 String ISBN = request.getParameter("ISBN");
		     String titolo = request.getParameter("titolo");
		     String lingua = request.getParameter("lingua");
		     int anno_pubblicazione = Integer.parseInt(request.getParameter("anno_pubblicazione"));
		     String categoria = request.getParameter("categoria");
			 String autore = request.getParameter("autore");
			 String casa_editrice = request.getParameter("casa_editrice");
			
			
			 LibroBean bean = new LibroBean();
			 bean.setISBN(ISBN);
			 bean.settitolo(titolo);
			 bean.setlingua(lingua);
			 bean.setanno_pubblicazione(anno_pubblicazione);
			 bean.setcategoria(categoria);
			 bean.setautore(autore);
			 bean.setcasa_editrice(casa_editrice);
			 
			 messaggio= "Operazione Fallita! <br> ISBN già esistente.";
			 request.setAttribute("messaggio", messaggio);
				
			 if (model.doAggiungiLibro (bean))
					{  
				    messaggio= "Libro aggiunto con successo";
					request.setAttribute("messaggio", messaggio);
			         
			            
			        }
				
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
