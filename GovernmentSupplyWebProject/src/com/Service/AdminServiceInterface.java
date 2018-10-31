package com.Service;

import com.al.dao.ClientDao;
import com.al.dao.ClientDaoImpl;
import com.al.dao.ProductDao;
import com.al.dao.ProductDaoImpl;
import com.al.dao.VendorDao;
import com.al.dao.VendorDaoImpl;
import com.al.model.Client;
import com.al.model.Product;
import com.al.model.Vendor;

public interface AdminServiceInterface
{
	ClientDao clientDao = new ClientDaoImpl();
	VendorDao vendorDao = new VendorDaoImpl();
	ProductDao productDao = new ProductDaoImpl();
	
	public void addVendor(Vendor vendor);
	public void deleteVendor(Vendor vendor);
	public void addClient(Client client);
	public void deleteClient(Client client);
	public void addProduct(Product product);
	public void deleteProduct(Product product);
}
