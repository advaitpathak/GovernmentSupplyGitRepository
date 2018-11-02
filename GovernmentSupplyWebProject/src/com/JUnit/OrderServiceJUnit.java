package com.JUnit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.Service.ClientService;
import com.Service.OrderService;
import com.Service.ProductService;
import com.al.dao.ClientDao;
import com.al.dao.ClientDaoImpl;
import com.al.dao.OrderDao;
import com.al.dao.OrderDaoImpl;
import com.al.dao.OrderExistsException;
import com.al.dao.ProductDao;
import com.al.dao.ProductDaoImpl;
import com.al.dao.ProductExistException;
import com.al.dao.SectionDaoImpl;
import com.al.model.Client;
import com.al.model.Order;
import com.al.model.Product;

public class OrderServiceJUnit {

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
	public void testGetOrder() {
		
	}

	@Test
	public void testGetAllOrders() {
		OrderService orderService = new OrderService();
		int actual = orderService.getAllOrders().size();
		OrderDao orderDao = new OrderDaoImpl();
		int expected = orderDao.getAllOrders().size();
		assertEquals(expected, actual);
	}

	@Test
	public void testAddOrder() {
		OrderService orderService = new OrderService();
		OrderDao orderDao = new OrderDaoImpl();
		try 
		{
			orderService.addOrder(new Order(10010, new ClientDaoImpl().getClient(1), new ProductDaoImpl().getProduct(1002), 200, "2014-02-01", "2014-05-01"));
			int expected = orderService.getAllOrders().size();
			int actual = orderDao.getAllOrders().size();
			assertEquals(expected, actual);
		} 
		catch (OrderExistsException e) 
		{
			e.printStackTrace();
		}

	}

	@Test
	public void testDeleteOrder() {
		OrderService orderService = new OrderService();
		OrderDao orderDaoImpl = new OrderDaoImpl();
		orderService.deleteOrder(orderDaoImpl.getOrder(10010));
		Order order = orderService.getOrder(10010);
		assertEquals(null, order);
	}

	@Test
	public void testGetOrderFromUser() {
		OrderService orderService = new OrderService();
		orderService.getOrderFromUser(10012, 1, 1002, 200, "2014-09-09", "2015-10-09");
		Order actual = orderService.getOrder(10012);
		OrderDao orderDao = new OrderDaoImpl();
		Order expected = orderDao.getOrder(10012);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetOrderOfParticularClient() {
		OrderService orderService = new OrderService();
		int actual = orderService.getOrderOfParticularClient(2).size();
		assertEquals(1, actual);
	}

}
