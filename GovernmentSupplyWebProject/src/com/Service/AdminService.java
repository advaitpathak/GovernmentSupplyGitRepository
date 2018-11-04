package com.Service;

import com.al.dao.ClientExistException;
import com.al.dao.ProductExistException;
import com.al.dao.VendorExistsException;
import com.al.model.AcceptedQuotes;
import com.al.model.Client;
import com.al.model.Product;
import com.al.model.Vendor;

public class AdminService implements AdminServiceInterface
{
	/**
	 * Method @addVendor(Vendor vendor)
	 * Inserts new Vendor into Vendor table through VendorDao
	 */
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
	/**
	 * Method @deleteVendor(Vendor vendor)
	 * Delete Vendor from Vendor table through VendorDao
	 */
	@Override
	public void deleteVendor(Vendor vendor) {
		vendorDao.deleteVendor(vendor);
	}
	/**
	 * Method @addClient(Client client)
	 * Inserts new Client into Client table through ClientDao
	 */
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
	/**
	 * Method @deleteClient(Client client)
	 * Inserts new Client into Vendor table through ClientDao
	 */
	@Override
	public void deleteClient(Client client) {
		clientDao.deleteClient(client);
	}
	/**
	 * Method @addProduct(Product product)
	 * Inserts new Product into Product table through ProductDao
	 */
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
	/**
	 * Method @deleteProduct(Product product)
	 * Delete Product from Product table through ProductDao
	 */
	@Override
	public void deleteProduct(Product product) 
	{
		productDao.deleteProduct(product);	
	}
}
