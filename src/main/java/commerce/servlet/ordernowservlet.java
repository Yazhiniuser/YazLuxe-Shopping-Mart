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

import org.apache.catalina.User;
import commerce.jdbc_conn.dbcon;
import commerce.dao.orderdao;
import commerce.model.*;


/**
 * Servlet implementation class ordernowservlet
 */
@WebServlet("/order-now")
public class ordernowservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

			
			user auth = (user) request.getSession().getAttribute("auth");
			
			if (auth != null) {
				String productId = request.getParameter("id");
                int productQuantity = Integer.parseInt(request.getParameter("quantity"));
                if (productQuantity <= 0) {
                	productQuantity = 1;
                }
                
                orders orderModel = new orders();
                orderModel.setId(Integer.parseInt(productId));
                orderModel.setUid(auth.getId());
                orderModel.setQuantity(productQuantity);
                orderModel.setDate(formatter.format(date));
                
                orderdao orderDao = new orderdao(dbcon.getConnection());
                boolean result = orderDao.insertOrder(orderModel);
                if(result) {
                	
                	ArrayList<cart> cart_list = (ArrayList<cart>) request.getSession().getAttribute("cart-list");
    				if (cart_list != null) {
    					for (cart c : cart_list) {
    						if (c.getId() == Integer.parseInt(productId)) {
    							cart_list.remove(cart_list.indexOf(c));
    							break;
    						}
    					}
    				}
                	response.sendRedirect("orders.jsp");
                } else {
                    out.println("order failed");
                }
                }else {
				response.sendRedirect("login.jsp");
			}
			}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
