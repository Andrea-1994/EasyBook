package prestitiPackage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import carrelloPackage.CarrelloBean;
import libriPackage.LibroBean;

import java.util.List;

/**
 * Servlet implementation class InvioPrestitoServlet
 */
public class InvioPrestitoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	static PrestitiManager model = new PrestitiManager();
	
	
	/**
     * @see HttpServlet#HttpServlet()
     */
	public InvioPrestitoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Metodo per creare un nuovo prestito.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String messaggio= null;
		
		HttpSession session = request.getSession(false);
		CarrelloBean cart = (CarrelloBean)session.getAttribute("cart");
		if(cart == null) {
			cart = new CarrelloBean();
			request.getSession().setAttribute("cart", cart);
		}

		String action = request.getParameter("action");

		try {
			if (action != null) {
				if (action.equalsIgnoreCase("prestito")) {
					String id = (String)session.getAttribute("num_tessera");
					model.doPrestito(id);
					List<LibroBean> libcart = cart.getlibri(); 
				      for(LibroBean beancart: libcart) {
					      model.doAggiorna(beancart.getISBN());
				      }
				    cart = new CarrelloBean();
					request.getSession().setAttribute("cart", cart);
				    messaggio= "L'ordine del prestito è stato effettuato con successo, <br> "
				    		+ "è possibile ritirare i libri in sede.";
				    request.setAttribute("messaggio", messaggio);
				    
				}
				
				
			}
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Carrello.jsp");
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
