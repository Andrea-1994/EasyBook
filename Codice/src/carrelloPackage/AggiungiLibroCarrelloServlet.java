package carrelloPackage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import libriPackage.LibriManager;

/**
 * 
 * servlet implementation class AggiungiLibroCarrelloServlet
 *
 */


public class AggiungiLibroCarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	static LibriManager model = new LibriManager();
	 
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	
	public AggiungiLibroCarrelloServlet() {
		super();
	}
	
	/**
	 * @see HttpServlet#doget(HattpServletRequest request, HttpServletResponse response)
	 * Aggiunge un libro al carrello.
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		CarrelloBean cart = (CarrelloBean)request.getSession().getAttribute("cart");
		if(cart == null) {
			cart = new CarrelloBean();
			request.getSession().setAttribute("cart", cart);
		}

		String action = request.getParameter("action");

		try {
			if (action != null) {
				if (action.equalsIgnoreCase("addC")) {
					String id = request.getParameter("id");
					cart.addlibro(model.doRetrieveByKey(id));
				} 
				
			}
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		
		request.getSession().setAttribute("cart", cart);
		request.setAttribute("cart", cart);

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
		doGet(request, response);
	}

}
