<%@page import="com.al.model.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="ErrorPage.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Place Order Portal</title>
</head>
<body>

<%
Object listobj=session.getAttribute("selectedProductsList");

List <Product> selectedProductsList=(List <Product>)listobj;
session.setAttribute("Count", selectedProductsList.size());
%>


<form method="post" action="PlaceOrder">
    <table border="2">

<tr>
			
            <th>ProductId</th>
            <th>ProductName</th>
            <th>ProductDetails</th>
            <th>QuantityRequired</th>
            <th>OrderPlacedDate</th>
            <th>deadLine</th>
           
        </tr>

       <%
       int count=1;
for(Product product:selectedProductsList)
{
	session.setAttribute("productId",product.getProductId());
	
	%>
            <tr>
            <% 
   	
	String quantity="quantity"+count;
	String OrderPlacedDate="OrderPlacedDate"+count;
	String deadLine="deadLine"+count;
	%>
				
                <td><%=product.getProductId() %></td>
				<td><%=product.getProductName() %></td>
				<td><%=product.getProductDetails() %> </td>
				<td align="left"><input type="text" name="<%=quantity%>" required/></td>
				<td align="left"><input type="text" name= "<%=OrderPlacedDate %>" required/></td>
				<td align="left"><input type="text" name="<%=deadLine%>" required/></td>
				
				<% count++;%>              
            </tr>
            
        <%
        }
	%>
    </table>
    <input type="submit" value="Confirm orders"/>
</form>   	

</body>
</html>