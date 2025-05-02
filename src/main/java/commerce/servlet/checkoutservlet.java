package commerce.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import commerce.dao.orderdao;
import commerce.jdbc_conn.dbcon;
import commerce.model.*;

/**
 * Servlet implementation class checkoutservlet
 */
@WebServlet("/check-out")
public class checkoutservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            ArrayList<cart> cart_list = (ArrayList<cart>) request.getSession().getAttribute("cart-list");
			user auth = (user) request.getSession().getAttribute("auth");
			if(cart_list != null && auth!=null) {
				for(cart c:cart_list) {
					orders order = new orders();
					order.setId(c.getId());
					order.setUid(auth.getId());
					order.setQuantity(c.getQuantity(0));
					order.setDate(formatter.format(date));
					
					orderdao oDao = new orderdao(dbcon.getConnection());
					boolean result = oDao.insertOrder(order);
					if(!result) break;
				}
				cart_list.clear();
				response.sendRedirect("orders.jsp");
			}else {
				if(auth==null) response.sendRedirect("login.jsp");
				response.sendRedirect("cart.jsp");
				}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
