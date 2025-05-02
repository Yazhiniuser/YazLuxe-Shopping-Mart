<%@page import="commerce.jdbc_conn.dbcon"%>
<%@page import="java.util.*" %>
<%@page import="commerce.model.*"%>
<%@page import="commerce.dao.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
user auth = (user) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}
productdao pd = new productdao(dbcon.getConnection());
List<product> products = pd.getAllProducts();

ArrayList<cart> cart_list = (ArrayList<cart>) session.getAttribute("cart-list");
if(cart_list !=null){
	request.setAttribute("cart_list",cart_list); 
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome to shopping cart</title>
<%@include file="includes/head.jsp"%>
<body>
	<%@include file="includes/navbar.jsp"%>	
	<div class="container">
		<div class="card-header my-3">All Products</div>	
	<div style="width: 100%; text-align: center; margin-bottom: 30px;">
    <img src="images/summer_collection.jpg" 
         alt="The Ultimate Fashion Collection" 
         style="width: 100%; max-width: 1200px; border-radius: 12px; box-shadow: 0 4px 12px rgba(0,0,0,0.1);">
	</div>
	<div style="width: 100%; text-align: center; margin-bottom: 30px;">
    <img src="images/new_collection.jpg" 
         alt="The Ultimate Fashion Collection" 
         style="width: 100%; max-width: 1200px; border-radius: 12px; box-shadow: 0 4px 12px rgba(0,0,0,0.1);">
	</div>
		<div class="row">
		<%
		if(!products.isEmpty())
		{
			for(product p:products)
			{
		%>
				<div class="col-md-3 my-3>">
				<div class="card w-100" style="width: 30rem;">
					<img src="product-images/<%= p.getImage() %>" class="card-img-top" >
					<div class="card-body">
						<h5 class="card-title"><%= p.getName() %></h5>
						<h6 class="price">Price: $<%= p.getPrice() %></h6>
						<h6 class="category">Category: <%= p.getCategory() %></h6>
						<div class="mt-3 d-flex justify-content-between"></div>
						<a href="add_to_cart?id=<%= p.getId() %>" class="btn btn-dark">Add to cart</a> 
						<a href="order-now?quantity=1&id=<%= p.getId() %>" class="btn btn-primary">Buy Now</a>
					</div>
				</div>
			</div>
			<%
			} 
		}
		%>
		</div>
	</div>
	<%@include file="includes/footer.jsp"%>
</body>
</html>