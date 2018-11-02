<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="ErrorPage.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
<h1> Government Supply Portal </h1>
<hr>
<h3> Login Page</h3>
<form method="post" action="LoginChecker">
Login id:<input type="text" name="loginId" required/><br>
Password : <input type="password" name="password" required/> <br><br>
User Type: <select name="type">
  <option value="Government Employee">Government Employee</option>
  <option value="Vendor">Vendor</option>
  <option value="Admin">Admin</option>
</select> <br><br>
<input type="submit" value ="submit" >
<hr>
</form>
</center>
</body>
</html>