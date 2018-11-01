package com.JUnit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.Service.ClientService;
import com.Service.VendorService;
import com.al.dao.ClientDaoImpl;
import com.al.dao.VendorDaoImpl;
import com.al.dao.VendorExistsException;
import com.al.model.Client;
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
		VendorDaoImpl vendorDaoImpl = new VendorDaoImpl();
		try 
		{
			vendorService.addVendor(new Vendor(703,"temp_name",5,"2014-09-04","temp_password"));
			int expected = vendorService.getAllVendors().size();
			int actual = vendorDaoImpl.getAllVendors().size();
			Vendor expectedVendor = vendorService.getVendor(703);
			Vendor actualVendor = vendorDaoImpl.getVendor(703);
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
		VendorDaoImpl vendorDaoImpl = new VendorDaoImpl();
		vendorService.deleteVendor(vendorDaoImpl.getVendor(703));
		Vendor vendor = vendorService.getVendor(703);
		assertEquals(null, vendor);
	
	}

	@Test
	public void testAddClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteClient() {
		ClientService clientService = new ClientService();
		ClientDaoImpl clientDaoImpl = new ClientDaoImpl();
		clientService.deleteClient(clientDaoImpl.getClient(3));
		Client client = clientService.getClient(3);
		assertEquals(null, client);

	}

	@Test
	public void testAddProduct() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteProduct() {
		fail("Not yet implemented");
	}

}
