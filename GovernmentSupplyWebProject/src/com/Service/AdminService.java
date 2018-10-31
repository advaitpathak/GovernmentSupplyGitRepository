package com.Service;

import com.al.dao.ClientExistException;
import com.al.dao.ProductExistException;
import com.al.dao.VendorExistsException;
import com.al.model.Client;
import com.al.model.Product;
import com.al.model.Vendor;

public class AdminService implements AdminServiceInterface
{

	@Override
	public void addVendor(Vendor vendor) {
		try
		{
			vendorDao.addVendor(vendor);
		} 
		catch (VendorExistsException e) 
		{	
			e.printStackTrace();
		}
	}

	@Override
	public void deleteVendor(Vendor vendor) {
		vendorDao.deleteVendor(vendor);
	}

	@Override
	public void addClient(Client client) {
		try 
		{
			clientDao.addClient(client);
		} 
		catch (ClientExistException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void deleteClient(Client client) {
		clientDao.deleteClient(client);
	}

	@Override
	public void addProduct(Product product) {
		try 
		{
			productDao.addProduct(product);
		} 
		catch (ProductExistException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void deleteProduct(Product product) 
	{
		productDao.deleteProduct(product);	
	}
}
