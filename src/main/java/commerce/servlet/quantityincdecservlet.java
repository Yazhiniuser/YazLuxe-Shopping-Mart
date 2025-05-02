package commerce.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import commerce.model.*;


/**
 * Servlet implementation class quantityincdecservlet
 */
@WebServlet("/quantity-inc-dec")
public class quantityincdecservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String action = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));
			
			ArrayList<cart> cart_list = (ArrayList<cart>) request.getSession().getAttribute("cart-list");

			if (action != null && id >= 1) {
				if (action.equals("inc")) {
					for (cart c : cart_list) {
						if (c.getId() == id) {
							int quantity = c.getQuantity(0);
							quantity++;
							c.setQuantity(quantity);
							response.sendRedirect("cart.jsp");
						}
					}
				}
				
			if (action.equals("dec")) {
				for (cart c : cart_list) {
					if (c.getId() == id && c.getQuantity(0) > 1) {
						int quantity = c.getQuantity(0);
						quantity--;
						c.setQuantity(quantity);
						break;
					}
				}
				response.sendRedirect("cart.jsp");
			}
		}else {
			response.sendRedirect("cart.jsp");
		}
     }
	}
}



	



