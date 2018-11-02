package com.JUnit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.Service.ClientService;
import com.Service.ProductService;
import com.Service.VendorService;
import com.al.dao.ClientDao;
import com.al.dao.ClientDaoImpl;
import com.al.dao.ClientExistException;
import com.al.dao.ProductDao;
import com.al.dao.ProductDaoImpl;
import com.al.dao.ProductExistException;
import com.al.dao.SectionDaoImpl;
import com.al.dao.VendorDao;
import com.al.dao.VendorDaoImpl;
import com.al.dao.VendorExistsException;
import com.al.model.Client;
import com.al.model.Product;
import com.al.model.Vendor;

public class AdminServiceJUnit {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddVendor() {
		VendorService vendorService = new VendorService();
		VendorDao vendorDao = new VendorDaoImpl();
		try 
		{
			vendorService.addVendor(new Vendor(703,"temp_name",5,"2014-09-04","temp_password"));
			int expected = vendorService.getAllVendors().size();
			int actual = vendorDao.getAllVendors().size();
			Vendor expectedVendor = vendorService.getVendor(703);
			Vendor actualVendor = vendorDao.getVendor(703);
			assertEquals(expected, actual);
			assertEquals(expectedVendor, actualVendor);
		} 
		catch (VendorExistsException e) 
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteVendor() {
		VendorService vendorService = new VendorService();
		VendorDao vendorDao = new VendorDaoImpl();
		vendorService.deleteVendor(vendorDao.getVendor(703));
		Vendor vendor = vendorService.getVendor(703);
		assertEquals(null, vendor);
	
	}

	@Test
	public void testAddClient() {
		ClientService clientService = new ClientService();
		ClientDao clientDao = new ClientDaoImpl();
		try 
		{
			clientService.addClient(new Client(3, "temp_name", "temp_email", "temp_password", "temp_contact"));
			int expected = clientService.getAllClient().size();
			int actual = clientDao.getAllClient().size();
			assertEquals(expected, actual);
		} 
		catch (ClientExistException e) 
		{
			e.printStackTrace();
		}		
	}

	@Test
	public void testDeleteClient() {
		ClientService clientService = new ClientService();
		ClientDao clientDaoImpl = new ClientDaoImpl();
		clientService.deleteClient(clientDaoImpl.getClient(3));
		Client client = clientService.getClient(3);
		assertEquals(null, client);
	}

	@Test
	public void testAddProduct() {
		ProductService productService = new ProductService();
		ProductDao productDao = new ProductDaoImpl();
		try 
		{
			productService.addProduct(new Product(1004, "temp_name", 12, "temp_details", new SectionDaoImpl().getSection(201)));
			int expected = productService.getAllProduct().size();
			int actual = productDao.getAllProduct().size();
			assertEquals(expected, actual);
		} 
		catch (ProductExistException e) 
		{
			e.printStackTrace();
		}
		
	}

	@Test
	public void testDeleteProduct() {
		ProductService productService = new ProductService();
		ProductDao productDao = new ProductDaoImpl();
		productService.deleteProduct(productDao.getProduct(1004));
		Product product = productService.getProduct(1004);
		assertEquals(null, product);
	}
}
