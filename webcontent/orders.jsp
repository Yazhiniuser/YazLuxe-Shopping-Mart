<%@page import="commerce.jdbc_conn.dbcon"  %>

<%@page import="commerce.dao.*"  %>
<%@page import="commerce.model.*"  %>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    DecimalFormat dcf = new DecimalFormat("#.##");
    request.setAttribute("dcf",dcf);
    user auth = (user) request.getSession().getAttribute("auth");
    List<orders> orders = null;
    if(auth != null){
    	request.setAttribute("auth" , auth);
        orders = new orderdao(dbcon.getConnection()).userOrders(auth.getId());
    	
    }else{
    	//response.sendRedirect("login.jsp");
    }
    
    ArrayList<cart> cart_list = (ArrayList<cart>) session.getAttribute("cart-list");
    if(cart_list !=null){
    	request.setAttribute("cart_list",cart_list); 
    }
    %>
<!DOCTYPE html>
<html>
<head>
<title>order page</title>
<%@include file="includes/head.jsp" %>
</head>
<body>
<%@include file="includes/navbar.jsp" %>

<div class="container">
		<div class="card-header my-3">All Orders</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Quantity</th>
					<th scope="col">Price</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
			<%
			if(orders != null){
				for(orders o:orders){%>
					<tr>
						<td><%=o.getDate() %></td>
						<td><%=o.getName() %></td>
						<td><%=o.getCategory() %></td>
						<td><%=o.getQuantity() %></td>
						<td><%=dcf.format(o.getPrice()) %></td>
						<td><a class="btn btn-sm btn-danger" href="cancel-order?id=<%=o.getOrderId()%>">Cancel Order</a></td>
					</tr>
				<%}
			}
			%>
			</tbody>
		</table>
	</div>

<%@include file="includes/footer.jsp" %>
</body>
</html> 