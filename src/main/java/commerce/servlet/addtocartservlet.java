package commerce.servlet;
import commerce.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import commerce.model.cart;

/**
 * Servlet implementation class addtocartservlet1
 */
@WebServlet("/add_to_cart")
public class addtocartservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            ArrayList<cart> cartlist = new ArrayList<>();
            
            int id = Integer.parseInt(request.getParameter("id"));
            cart cm = new cart();
            cm.setId(id);
            cm.setQuantity(1);
            
            HttpSession session = request.getSession();
            ArrayList<cart> cart_list = (ArrayList<cart>) session.getAttribute("cart-list");
            
            if (cart_list == null) {
                cartlist.add(cm);
                session.setAttribute("cart-list", cartlist);
                response.sendRedirect("index.jsp");
            }
            else {
                cartlist = cart_list;

                boolean exist = false;
                for (cart c : cart_list) {
                    if (c.getId() == id) {
                        exist = true;
                        out.println("<h3 style='color:crimson; text-align: center'>Item Already in Cart. <a href='cart.jsp'>GO to Cart Page</a></h3>");
                    }
                }

                if (!exist) {
                    cartlist.add(cm);
                    response.sendRedirect("index.jsp");
                }
            }

        }
	}

}