<%@page import="com.al.model.Order"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%

Object listobj=session.getAttribute("allOrders");

List <Order> allOrderList=(List <Order>)listobj;
%>

<center>
<h1> Welcome Client... </h1>
<h3> Here are the orders you have placed</h3>
</center>


 <table border="2">
<tr>
			
            <th>OrderId</th>
            <th>ProductName</th>
            <th>ProductDetails</th>
            <th>QuantityRequired</th>
            <th>OrderPlacedDate</th>
            <th>deadLine</th>
 </tr>


       <%
for(Order order:allOrderList)
{
%>
            <tr>
                <td><%=order.getOrderId() %></td>
				<td><%=order.getProduct().getProductName() %></td>
				<td><%=order.getProduct().getProductDetails() %> </td>
				<td><%=order.getQuantityRequired()%> </td>
				<td><%=order.getOrderPlacedDate() %></td>
				<td><%=order.getDeadline() %></td>
				
                
            </tr>
        <% } %>
    </table>
<br>
<br>
<form method='post' action='GovernmentEmployeePortal.jsp'><br><input type='submit' value='Back'/>
</form>



</body>


</html>