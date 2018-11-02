<%@page import="com.al.model.AcceptedQuotes"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="ErrorPage.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Accepted Quotations Portal</title>
</head>
<body>
<%
Object listobj=session.getAttribute("allAcceptedQuotesList");
List <AcceptedQuotes> allAcceptedQuotes=(List <AcceptedQuotes>)listobj;
System.out.println(listobj);
%>
<center>
<h1> Accepted Quotations List </h1>
</center>
<form method="post" action="SelectedProducts">
    <table border="2">

<tr>
			
            <th>AcceptedQuoteId</th>
            <th>OrderId</th>
            <th>VendorId</th>
            <th>Quantity</th>
            <th>TotalCost</th>
           
 </tr>

       <%
for(AcceptedQuotes acceptedQuotes:allAcceptedQuotes)
{
%>
            <tr>
                <td><%=acceptedQuotes.getAcceptedQuoteId() %></td>
				<td><%=acceptedQuotes.getOrder().getOrderId() %></td>
				<td><%=acceptedQuotes.getVendor().getVendorId()%> </td>
				<td><%=acceptedQuotes.getQuantity()%> </td>
				<td><%=acceptedQuotes.getTotalCost() %></td>
            </tr>
        <% } %>
    </table>
    </form>
    
<br>
<br>
<form method='post' action='VendorPortal.jsp'><br><input type='submit' value='Back'/>
</form>
    
</body>
</html>