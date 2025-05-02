package commerce.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class logoutservlet
 */
@WebServlet("/log-out")
public class logoutservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			response.setContentType("text/html;charset=UTF-8");
			try (PrintWriter out = response.getWriter()) {
				if(request.getSession().getAttribute("auth")!=null) {
					request.getSession().removeAttribute("auth");
					response.sendRedirect("login.jsp");
				}else {
					response.sendRedirect("index.jsp");
				}
			} 
		}
}
