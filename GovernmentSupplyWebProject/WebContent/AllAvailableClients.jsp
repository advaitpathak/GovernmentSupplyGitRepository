<%@page import="com.al.model.Client"%>
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
<% 	Object object = session.getAttribute("allClientList");
	List<Client> clientList = (List<Client>) object;
%>
<table border = 1>
<tr><td>Client Id</td><td>Client Name</td><td>Client Email</td><td>Client Password</td><td>Client Contact No</td></tr>
	<%for(Client client : clientList) {%>
	<tr>
	<td><%=client.getClientId()%></td>
	<td><%=client.getClientName()%></td>
	<td><%=client.getClientEmail()%></td>
	<td><%=client.getClientPassword()%></td>
	<td><%=client.getClientContactNo()%></td>
	</tr>	
	<%} %>
</table>
<br>
<br>
<form method='post' action='AdminPortal.jsp'><br><input type='submit' value='Back'/>
</form>

</body>
</html>