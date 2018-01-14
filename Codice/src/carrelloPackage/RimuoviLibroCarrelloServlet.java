package carrelloPackage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import libriPackage.LibriManager;

/**
 * Servlet implementation class RimuoviLibroCarrelloServlet
 */

public class RimuoviLibroCarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	

	static LibriManager model = new LibriManager();
	
	
	/**
     * @see HttpServlet#HttpServlet()
     */
	
	public RimuoviLibroCarrelloServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Metodo per la rimozione di un libro dal carrello.
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		HttpSession session = request.getSession(false);
		CarrelloBean cart = (CarrelloBean)session.getAttribute("cart");
		if(cart == null) {
			cart = new CarrelloBean();
			request.getSession().setAttribute("cart", cart);
		}

		String action = request.getParameter("action");

		try {
			if (action != null) {
				if (action.equalsIgnoreCase("deleteC")) {
					String id = request.getParameter("id");
					cart.deletelibro(model.doRetrieveByKey(id));
					request.getSession().setAttribute("cart", cart);
					request.setAttribute("cart", cart);
					
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
