<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Portal</title>
</head>
<body>
<%	Object productIdObj = session.getAttribute("addProductId");
	Integer addProductId = (Integer) productIdObj; 
	Object clientIdObj = session.getAttribute("addClientId");
	Integer addClientId = (Integer) clientIdObj;
	Object vendorIdObj = session.getAttribute("addVendorId");
	Integer addVendorId = (Integer) vendorIdObj;
%>
<center><h2>Welcome Admin</h2></center>
<br>
<br>
<form method='post' action='AddProduct'>
<table border="2"><tr><td>Product Id</td><td>Product Name</td><td>Product Cost</td><td>Section Id</td><td>Product Detail</td></tr>
<tr>
<td><%=addProductId %></td>
<td><input type="text" name = "productName" required/></td>
<td><input type="text" name = "productCost" required/></td>
<td><input type="text" name = "sectionId" required/></td>
<td><input type="text" name = "productDetail" required/></td>
<td><input type='submit' value='Add Product' required/></td>
</tr>
</table>
</form>
<br>
<br>
<form method='post' action='DeleteProduct'>
Id:-<input type="text" name = "deleteProductId" required/>
<input type='submit' value='Delete Product' required/>
</form>

<br>
<br>
<form method='post' action='AddClient'>
<table border="2"><tr><td>Client Id</td><td>Client Name</td><td>Client Email</td><td>Client Password</td><td>Client Contact No</td></tr>
<tr>
<td><%=addClientId %></td>
<td><input type="text" name = "clientName" required/></td>
<td><input type="text" name = "clientEmail" required/></td>
<td><input type="text" name = "clientPassword" required/></td>
<td><input type="text" name = "clientContactNo" required/></td>
<td><input type='submit' value='Add Client' value="Client Id"/></td>
</tr>
</table>
</form>

<br>
<br>
<form method='post' action='DeleteClient'>
Id:-<input type="text" name = "deleteClientId" required/>
<input type='submit' value='Delete Client' />
</form>

<br>
<br>
<form method='post' action='AddVendor'>
<table border="2"><tr><td>Vendor Id</td><td>Vendor Name</td><td>Vendor Rating</td><td>Established Date (YYYY-MM-DD)</td><td>Vendor Password</td></tr>
<tr>
<td><%=addVendorId %></td>
<td><input type="text" name = "vendorName" required/></td>
<td><input type="text" name = "vendorRating" required/></td>
<td><input type="text" name = "establishedDate"  required/></td>
<td><input type="text" name = "vendorPassword" required/></td>
<td><input type='submit' value='Add Vendor' /></td>
</tr>
</table>
</form>

<br>
<br>
<form method='post' action='DeleteVendor'>
Id:-<input type="text" name = "deleteVendorId" required/>
<input type='submit' value='Delete Vendor' />
</form>
<br>
<br>
<form method='post' action='UpdateVendorRanking'>
<table border="2"><tr><td>Vendor Id</td><td>New Vendor Rank</td></tr>
<tr>
<td><input type="text" name = "vendorIdToUpdate" required/></td>
<td><input type="text" name = "updatedVendorRating" required/></td>
<td><input type='submit' value='Update'/></td>
</tr>
</table>
</form>
<br>
<br>
<form method="post" action="Logout"><br><input type="submit" value="Logout"/>
</form>

</body>
</html>