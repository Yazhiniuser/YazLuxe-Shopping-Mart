package commerce.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import commerce.dao.userdao;
import commerce.jdbc_conn.dbcon;
import commerce.model.user;

/**
 * Servlet implementation class loginservlet
 */
@WebServlet("/user-login")
public class loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String email = request.getParameter("login-email");
			String password = request.getParameter("login-password");

			userdao udao = new userdao(dbcon.getConnection());
			user user = udao.userLogin(email, password);
			
			if (user != null) {
				out.print("user login");
				request.getSession().setAttribute("auth", user);
			System.out.print("user logged in");
				response.sendRedirect("index.jsp");
			} else {
				out.println("there is no user");
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
