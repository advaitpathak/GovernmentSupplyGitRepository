<%@page import="com.al.model.Order"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%	Object vendorIdObj = session.getAttribute("vendorId");
	Integer vendorId = Integer.parseInt(vendorIdObj.toString());
	Object orderObj = session.getAttribute("order");
	Order order = (Order) orderObj;
%>
<center>
<h1>Quote already placed by you with vendorId <%=vendorId %> for orderId <%=order.getOrderId() %></h1>
</center>
<br>
<br>
<br>
<form method='post' action='VendorPortal.jsp'><br><input type='submit' value='Back'required/>
</form>

</body>
</html>