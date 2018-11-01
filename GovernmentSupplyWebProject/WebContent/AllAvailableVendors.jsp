<%@page import="com.al.model.Vendor"%>
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
<% 	Object object = session.getAttribute("allVendorList");
	List<Vendor> vendorList = (List<Vendor>) object;
%>
<table border = 1>
<tr><td>Vendor Id</td><td>Vendor Name</td><td>Vendor Rating</td><td>Established Date</td><td>Vendor Password</td></tr>
	<%for(Vendor vendor : vendorList) {%>
	<tr>
	<td><%=vendor.getVendorId()%></td>
	<td><%=vendor.getVendorName()%></td>
	<td><%=vendor.getVendorRating()%></td>
	<td><%=vendor.getEstablishedDate()%></td>
	<td><%=vendor.getVendorPassword()%></td>
	</tr>	
	<%} %>
</table>
<br>
<br>
<form method='post' action='AdminPortal.jsp'><br><input type='submit' value='Back'/>
</form>

</body>
</html>