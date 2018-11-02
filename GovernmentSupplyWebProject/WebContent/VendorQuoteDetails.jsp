<%@page import="com.al.model.Quotation"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="ErrorPage.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 	Object object = session.getAttribute("quotationOfVendorList");
	List<Quotation> vendorQuotationList = (List<Quotation>) object;
%>
<H1>Your quotations !</H1>
<table border = 1>
<tr><td>Quote Id</td><td>Order Id</td><td>Vendor Id</td><td>Quoted Cost</td><td>Estimated Delivery Date</td><td>Quoted Quantity</td></tr>
	<% for(Quotation vendorQuote : vendorQuotationList) {%>
	<tr>
	<td><%=vendorQuote.getQuoteId()%></td>
	<td><%=vendorQuote.getOrder().getOrderId()%></td>
	<td><%=vendorQuote.getVendor().getVendorId()%></td>
	<td><%=vendorQuote.getQuoteCost()%></td>
	<td><%=vendorQuote.getEstimateDeliveryDate()%></td>
	<td><%=vendorQuote.getQuotedQuantity()%></td>
	</tr>	
	<%} %>
</table>
<br><br>
<form method='post' action='VendorPortal.jsp'><br><input type='submit' value='Back'required/>
</form>
</body>
</html>