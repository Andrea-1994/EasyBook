package libriPackage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import carrelloPackage.CarrelloBean;


/**
 * Servlet implementation class VisualizzaCatalogoServlet
 */
public class VisualizzaCatalogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	static LibriManager model = new LibriManager();
	 
	/**
     * @see HttpServlet#HttpServlet()
     */
	public VisualizzaCatalogoServlet() {
		super();
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Metodo per visualizzare il catalogo, ricercare un libro e visulizzare informazioni sul libro.
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
				if (action.equalsIgnoreCase("read")) {
					String id = request.getParameter("id");
					request.removeAttribute("libro");
					request.setAttribute("libro", model.doRetrieveByKey(id));
				} else if (action.equalsIgnoreCase("Search")) {
					String id = request.getParameter("titolo");
					request.removeAttribute("libro");
					request.setAttribute("libro", model.doRetrieveBySearch(id));
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
