<%@page import="java.util.List"%>
<%@page import="com.al.model.Order"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="error.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Exception Page</title>
</head>
<body>

<%Object object = request.getAttribute("exceptionName"); 
String exceptionname = (String) object;
Object object2 = request.getAttribute("OriginPage");
String originPage = (String) object2;
%>
	<tr>
	<b>Error occured<b>
	<td><%=exceptionname%></td>
	</tr>	
<br>
<br>
<br>

<form method='post' action="<%=originPage %>"><br><input type='submit' value='Back'/>
</form>
</body>
</html>