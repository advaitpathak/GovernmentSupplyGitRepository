package com.al.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.al.connection.DBConnection;
import com.al.model.AcceptedQuotes;
import com.al.model.Product;
/**
 * 
 * @author Administrator
 *	@AcceptedQuotesDaoImpl implements @AcceptedQuotesDao interface to perform the CRUD operations 
 */
public class AcceptedQuotesDaoImpl implements AcceptedQuotesDao 
{
	private static List<AcceptedQuotes> allacceptedQuotesList = new ArrayList<AcceptedQuotes>();
	
	/**
	 * Method getAllAcceptedQuotes gets all the accepted quotes of different vendors
	 * and returns a list of all accepted quotes
	 */
	@Override
	public List<AcceptedQuotes> getAllAcceptedQuotes() 
	{
		allacceptedQuotesList = new ArrayList<AcceptedQuotes>();
		try 
		{	
			Connection connection=DBConnection.getConnection();
			String sqlQuery="select * from "+TABLEAcceptedQuotes;
			
			/**
			 * establish connection and execute the sql query
			 */
			PreparedStatement pst=connection.prepareStatement(sqlQuery);
			
			pst.executeQuery();
			ResultSet rs=pst.getResultSet();
			
			/**
			 * add all the accepted quotes in the list
			 */
			while(rs.next())
			{
				int acceptedQuoteId = rs.getInt(COLacceptedQuoteId);
				int orderId = rs.getInt(COLorderId);
				int vendorId = rs.getInt(COLvendorId);				
				int quantity = rs.getInt(COLquantity);
				int totalCost = rs.getInt(COLtotalCost);
				AcceptedQuotes acceptedQuotes = new AcceptedQuotes(acceptedQuoteId, new OrderDaoImpl().getOrder(orderId), new VendorDaoImpl().getVendor(vendorId), quantity, totalCost);
				allacceptedQuotesList.add(acceptedQuotes);
			}
		
			return  allacceptedQuotesList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * method addAcceptedQuote adds a accepted quote to the list
	 * and returns void 
	 */
	@Override
	public void addAcceptedQuote(AcceptedQuotes acceptedQuote) throws QuoteExistException {
		if(allacceptedQuotesList.contains(acceptedQuote))
		{
			throw new QuoteExistException();
		}
		
		try 
		{
			/**
			 * Establish connection
			 */
			Connection connection = DBConnection.getConnection();
			String sqlquery="insert into " + TABLEAcceptedQuotes+" values(?,?,?,?,?)";
		
			/**
			 * execute the sql query and add an accepted quote by setting different
			 * parameters in the PreparedStatement
			 */
			PreparedStatement pst = connection.prepareStatement(sqlquery);
			pst.setInt(1, acceptedQuote.getAcceptedQuoteId());
			pst.setInt(2, acceptedQuote.getOrder().getOrderId());
			pst.setInt(3, acceptedQuote.getVendor().getVendorId());
			pst.setInt(4, acceptedQuote.getQuantity());
			pst.setInt(5, acceptedQuote.getTotalCost());
		
			pst.executeUpdate();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
