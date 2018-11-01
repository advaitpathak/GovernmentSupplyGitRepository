<%@page import="java.util.List"%>
<%@page import="com.al.model.Order"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border = 1>
<%Object object = session.getAttribute("order"); 
Order temp_order = (Order) object;
Object object2 = session.getAttribute("quoteId");
Integer temp_quoteId = (Integer) object2;
%>
<tr><td>Order Id</td><td>Client Id</td><td>Product Id</td><td>Quantity Required</td><td>Order Placed Date</td><td>Deadline</td></tr>
	<tr>
	<td><%=temp_order.getOrderId()%></td>
	<td><%=temp_order.getClient().getClientId()%></td>
	<td><%=temp_order.getProduct().getProductId()%></td>
	<td><%=temp_order.getQuantityRequired()%></td>
	<td><%=temp_order.getOrderPlacedDate()%></td>
	<td><%=temp_order.getDeadline()%></td>
	</tr>	
</table>
<br>
<br>
<br>
<table border="2">
<tr><td>Quote Id</td><td>Vendor Id</td><td>Quoted Cost</td><td>Estimated Delivery Date</td><td>Quoted Quantity</td></tr>
	<tr>
	<form method = "post" action = "QuotationConfirmation">
	<td><%=temp_quoteId%></td>
	<td><%=session.getAttribute("vendorId")%></td>
	<td><input type="text" name = "quotedCost" required/></td>
	<td><input type="text" name = "estimatedDeliveryDate"required/></td>
	<td><%=session.getAttribute("quoteQuantity") %></td>
	<td><input type="submit" value = "Quote" required/></td>
	</form>
	</tr>
</table>
<form method='post' action='VendorPortal.jsp'><br><input type='submit' value='Back'/>
</form>
</body>
</html>