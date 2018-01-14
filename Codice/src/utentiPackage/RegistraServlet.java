package utentiPackage;

import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class RegistrazioneServlet
 */

public class RegistraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	static UtentiManager model = new UtentiManager();
	
	
	/**
     * @see HttpServlet#HttpServlet()
     */
	public RegistraServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	//public int count;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Metodo per effettuare la registrazione di un nuovo utente.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
	    String messaggio = null;
	  
		
		try{
	   
	    if (action != null) {
	     if (action.equalsIgnoreCase("registra")){
			 String num_tessera = request.getParameter("num_tessera");
		     String pass = request.getParameter("pass");
		     String nome_S = request.getParameter("nome_S");
		     String cognome_S = request.getParameter("cognome_S");
		     String data_nascita_S = request.getParameter("data_nascita_S");
			 String indirizzo = request.getParameter("indirizzo");
			
			 UtenteBean bean = new UtenteBean();
			 bean.setnum_tessera(num_tessera);
			 bean.setpass(pass);
			 bean.setnome_S(nome_S);
			 bean.setcognome_S(cognome_S);
			 bean.setdata_nascita_S(data_nascita_S);
			 bean.setindirizzo(indirizzo);
			 
			 messaggio= "Registrazione Fallita! <br> numero di tessera già esistente.";
			 request.setAttribute("messaggio", messaggio);
				
			 if (model.registra (bean))
					{  
				    messaggio= "Registrazione effettuata con successo";
					request.setAttribute("messaggio", messaggio);
			         
			            
			        }
				
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
	

	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ListaUtenti.jsp");
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
