<%@page import="com.al.model.Quotation"%>
<%@page import="com.Service.QuotationService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="ErrorPage.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirmation</title>
</head>
<body>
<H1>Your quotation is placed !</H1>
<table border = 1>
<%Object object = session.getAttribute("confirmedQuotation"); 
Quotation temp_quotation = (Quotation) object;
%>
<tr><td>Quote Id</td><td>Order Id</td><td>Vendor Id</td><td>Quoted Cost</td><td>Estimated Delivery Date</td><td>Quoted Quantity</td></tr>
	<tr>
	<td><%=temp_quotation.getQuoteId()%></td>
	<td><%=temp_quotation.getOrder().getOrderId()%></td>
	<td><%=temp_quotation.getVendor().getVendorId()%></td>
	<td><%=temp_quotation.getQuoteCost()%></td>
	<td><%=temp_quotation.getEstimateDeliveryDate()%></td>
	<td><%=temp_quotation.getQuotedQuantity()%></td>
	</tr>	
</table>
</body>
</html>