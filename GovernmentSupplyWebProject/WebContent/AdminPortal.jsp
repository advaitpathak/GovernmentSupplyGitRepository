<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<br>
<br>
<form method='post' action='AddProduct'>
<center>
<input type="text" name = "productId" value="Id"/>
<input type="text" name = "productName" value="Name"/>
<input type="text" name = "productCost" value="Cost"/>
<input type="text" name = "sectionId" value="Section Id"/>
<input type="text" name = "productDetail" value="Details"/>
<br>
<br>
<input type='submit' value='Add Product'/>
</center>
</form>

<br>
<br>

<form method='post' action='DeleteProduct'>
<center>
<input type="text" name = "deleteProductId" value="Id"/>
<input type='submit' value='Delete Product'/>
</center>
</form>

<br>
<br>
<form method='post' action='AddClient'>
<center>
<input type="text" name = "clientId" value="Id"/>
<input type="text" name = "clientName" value="Name"/>
<input type="text" name = "clientEmail" value="Email"/>
<input type="text" name = "clientPassword" value="Password"/>
<input type="text" name = "clientContactNo" value="Contact No"/>
<br>
<br>
<input type='submit' value='Add Client' value="Client Id"/>
</center>
</form>

<br>
<br>
<form method='post' action='DeleteClient'>
<center>
<input type="text" name = "deleteClientId" value="Id"/>
<input type='submit' value='Delete Client'/>
</center>
</form>

<br>
<br>
<form method='post' action='AddVendor'>
<center>
<input type="text" name = "vendorId" value="Id"/>
<input type="text" name = "vendorName" value="Name"/>
<input type="text" name = "vendorRating" value="Rating"/>
<input type="text" name = "establishedDate" value="Establied Date(YYYY-MM-DD)"/>
<input type="text" name = "vendorPassword" value="Password"/>
<br>
<br>
<input type='submit' value='Add Vendor'/>
</center>
</form>

<br>
<br>
<form method='post' action='DeleteVendor'>
<center>
<input type="text" name = "deleteVendorId" value="Id"/>
<input type='submit' value='Delete Client'/>
</center>
</form>
<br>
<br>
<form method="post" action="Logout"><br><input type="submit" value="Logout"/>
</form>

</body>
</html>