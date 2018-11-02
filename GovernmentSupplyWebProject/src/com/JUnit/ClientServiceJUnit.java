package com.JUnit;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.Service.ClientService;
import com.al.dao.ClientDao;
import com.al.dao.ClientDaoImpl;
import com.al.dao.ClientExistException;
import com.al.model.Client;

public class ClientServiceJUnit {

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
	public void testGetClient() {
		ClientService clientService = new ClientService();
		ClientDao clientDao = new ClientDaoImpl();
		Client client = clientService.getClient(0);
		assertEquals(null, client);
	}

	@Test
	public void testGetAllClient() {
		ClientService clientService = new ClientService();
		int actual = clientService.getAllClient().size();
		ClientDao clientDao = new ClientDaoImpl();
		int expected = clientDao.getAllClient().size();
		assertEquals(expected, actual);
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

}
