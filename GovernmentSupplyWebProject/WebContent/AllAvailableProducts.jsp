<%@page import="java.util.List"%>
<%@page import="com.al.model.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 	Object object = session.getAttribute("allProductList");
	List<Product> productList = (List<Product>) object;
%>
<table border = 1>
<tr><td>Product Id</td><td>Product Name</td><td>Product Cost</td><td>Section Id</td><td>Product Details</td></tr>
	<%for(Product product : productList) {%>
	<tr>
	<td><%=product.getProductId()%></td>
	<td><%=product.getProductName()%></td>
	<td><%=product.getProductCost()%></td>
	<td><%=product.getSection().getSectionId()%></td>
	<td><%=product.getProductDetails()%></td>
	</tr>	
	<%} %>
</table>
<br>
<br>
<form method='post' action='AdminPortal.jsp'><br><input type='submit' value='Back'/>
</form>

</body>
</html>