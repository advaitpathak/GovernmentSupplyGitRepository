package com.JUnit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.Service.AcceptedQuotesService;
import com.al.dao.AcceptedQuotesDao;
import com.al.dao.AcceptedQuotesDaoImpl;
import com.al.dao.OrderDaoImpl;
import com.al.dao.QuoteExistException;
import com.al.dao.VendorDaoImpl;
import com.al.model.AcceptedQuotes;
import com.al.model.Order;

public class AcceptedQuotesServiceJUnit {

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
	public void testGetAllAcceptedQuotes() {
		AcceptedQuotesDao acceptedQuotesDao = new AcceptedQuotesDaoImpl();
		int expected = acceptedQuotesDao.getAllAcceptedQuotes().size();
		AcceptedQuotesService acceptedQuotesService = new AcceptedQuotesService();
		int actual = acceptedQuotesService.getAllAcceptedQuotes().size();
		assertEquals(expected, actual);
	}

	@Test
	public void testAddAcceptedQuoteAcceptedQuotes() {
		AcceptedQuotesDao acceptedQuotesDao = new AcceptedQuotesDaoImpl();
		AcceptedQuotesService acceptedQuotesService = new AcceptedQuotesService();
		try 
		{
			acceptedQuotesDao.addAcceptedQuote(new AcceptedQuotes(409, new OrderDaoImpl().getOrder(10001), new VendorDaoImpl().getVendor(701), 25, 45000));
			int expected = acceptedQuotesDao.getAllAcceptedQuotes().size();
			acceptedQuotesService.addAcceptedQuote(new AcceptedQuotes(409, new OrderDaoImpl().getOrder(10001), new VendorDaoImpl().getVendor(701), 25, 45000));
			int actual = acceptedQuotesService.getAllAcceptedQuotes().size();
			assertEquals(expected, actual);
		} 
		catch (QuoteExistException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void tesForNulltAddAcceptedQuoteAcceptedQuotes() {
		AcceptedQuotesDao acceptedQuotesDao = new AcceptedQuotesDaoImpl();
		AcceptedQuotesService acceptedQuotesService = new AcceptedQuotesService();
		try 
		{
			acceptedQuotesDao.addAcceptedQuote(new AcceptedQuotes(Integer.parseInt(null), new OrderDaoImpl().getOrder(10001), new VendorDaoImpl().getVendor(701), 25, 45000));
			int expected = acceptedQuotesDao.getAllAcceptedQuotes().size();
			acceptedQuotesService.addAcceptedQuote(new AcceptedQuotes(Integer.parseInt(null), new OrderDaoImpl().getOrder(10001), new VendorDaoImpl().getVendor(701), 25, 45000));
			int actual = acceptedQuotesService.getAllAcceptedQuotes().size();
			assertEquals(expected, actual);
		} 
		catch (QuoteExistException e) 
		{
			e.printStackTrace();
		}
	}

//	@Test
//	public void testAddAcceptedQuoteIntIntIntInt() {
//		
//	}

}
