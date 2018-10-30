<%@page import="com.al.model.Order"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vendor Portal</title>
</head>
<body>
<table border = 1>
<%Object object = session.getAttribute("allOrdersList"); 
List<Order> orderObjList = (List<Order>) object;%>
<tr><td>Order Id</td><td>Client Id</td><td>Product Id</td><td>Quantity Required</td><td>Order Placed Date</td><td>Deadline</td><td>Quote For Order</tr>
	<tr>
	<% for(Order order : orderObjList ){%>
	<td><%=order.getOrderId()%></td>
	<td><%=order.getClient().getClientId()%></td>
	<td><%=order.getProduct().getProductId()%></td>
	<td><%=order.getQuantityRequired()%></td>
	<td><%=order.getOrderPlacedDate()%></td>
	<td><%=order.getDeadline()%></td>
	<td><form method = "post" action = "VendorQuotation">
		<input type="submit" name="Quote" value = "<%=order.getOrderId()%>"/></form>
	</td>
	</tr>	
	<% }%>
</table>
<form method='post' action='LoginPage.jsp'><br><input type='submit' value='Back'/>
</form>
<form method='post' action='GetVendorQuotes'><br><input type='submit' value='Get Quotes'/>
</form>

</body></html>