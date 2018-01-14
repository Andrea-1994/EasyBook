package utentiPackage;

import java.io.IOException;
import java.sql.SQLException;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class LoginServlet.
 */

public class LoginServlet extends HttpServlet  {
	private static final long serialVersionUID = 1L;


	static UtentiManager model = new UtentiManager();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Metodo per effettuare il login.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	
		String n=request.getParameter("username");  
        String p=request.getParameter("userpass");
        String messaggio = null;

      

        HttpSession session = request.getSession(false);
        
        
       
        
		try {
			if (model.Accesso(n,p)) 
			{  
				session.setAttribute("num_tessera", n);
			
				
	       
	        }
			else{  
				session.setAttribute("num_tessera", null);
			    session.invalidate(); 
				messaggio= "ID utente o password errati";
				request.setAttribute("messaggio", messaggio);
		        request.getRequestDispatcher("/Login.jsp").forward(request, response);  
			}
			
		 
		 }catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		
       try {
        	request.removeAttribute("utente");
			request.setAttribute("utente", model.doNome(n,p));
			session.setAttribute("nome", (request.getAttribute("utente")));
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/LoginEffettuato.jsp");
			dispatcher.forward(request, response);
			
			
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
        
	
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
