package com.al.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.al.connection.DBConnection;
import com.al.model.Client;
import com.al.model.Quotation;
import com.al.model.Quotation;
/**
 * 
 * @author Administrator
 *	@QuotationDaoImpl implements @QuotationDao interface to perform the CRUD operations 
 */

public class QuotationDaoImpl implements QuotationDao
{
	private static List<Quotation> allquotationList = new ArrayList<Quotation>();
	
	/**
	 * getQuotation() method gives particular Quotation using  specified Quotation Id
	 */
	@Override
	public Quotation getQuotation(int quoteId)
	{	
		allquotationList = new ArrayList<Quotation>();
		try  
		  {
			Quotation dummy = new Quotation();
			dummy.setQuoteId(quoteId);
			/**
			 * Checks if it is already present in list 
			 * If  it is present in the list then return from the list
			 */
			if(allquotationList.contains(dummy))
			{
				int index =allquotationList.indexOf(dummy);
				return allquotationList.get(index);
			}
			
			Connection connection = DBConnection.getConnection();
			String sqlquery = "Select * from "+TABLEQuotation +" where "+ COLquoteId+"=?";
			
			/**
			 * Create connection with database and  executes query
			 * 	
			*/
			
			PreparedStatement pst = connection.prepareStatement(sqlquery);
			pst.setInt(1,quoteId);
			pst.executeQuery();
			ResultSet rs = pst.getResultSet();
			
			/**
			 * Until there is data in resultSet
			 */
			
			if(rs.next()==false)
			{
				return null;
			}
			int order_Id = rs.getInt(COLorderId);
			int vendor_Id  = rs.getInt(COLvendorId);
			int quoted_Cost  = rs.getInt(COLquotedCost);
			String estimated_Delivery = rs.getString(COLestimatedDelivery);
			int quoted_Quantity  = rs.getInt(COLquotedQuantity);
			
			/**
			 * Creates Quotation Object with its required parameter
			 * Add it to the list
			 * return list
			 */
			Quotation quotationObj = new Quotation(quoteId,new OrderDaoImpl().getOrder(order_Id),new VendorDaoImpl().getVendor(vendor_Id), quoted_Cost, estimated_Delivery, quoted_Quantity);
			allquotationList.add(quotationObj);
			return quotationObj;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return null;
	}

	/**
	 * getAllQuotation()) method returns list of all Quotation
	 */
	@Override
	public List<Quotation> getAllQuotation() 
	{
		allquotationList=new ArrayList<Quotation>();
		try {
			
			
			Connection connection=DBConnection.getConnection();
			String sqlQuery="select * from "+TABLEQuotation;
			
			/**
			 * establish connection and execute the sql query
			 */
			
			PreparedStatement pst=connection.prepareStatement(sqlQuery);
			
			pst.executeQuery();
			ResultSet rs=pst.getResultSet();
			
			while(rs.next())
			{
				int quote_Id=rs.getInt(COLquoteId);
				int order_Id = rs.getInt(COLorderId);
				int vendor_Id  = rs.getInt(COLvendorId);
				int quoted_Cost  = rs.getInt(COLquotedCost);
				String estimated_Delivery = rs.getString(COLestimatedDelivery);
				int quoted_Quantity  = rs.getInt(COLquotedQuantity);
				/**
				 * Creates Quotation Object with its required parameter
				 * Add it to the list
				 * return list
				 */
				Quotation quotationObj = new Quotation(quote_Id,new OrderDaoImpl().getOrder(order_Id),new VendorDaoImpl().getVendor(vendor_Id), quoted_Cost, estimated_Delivery, quoted_Quantity);
				allquotationList.add(quotationObj);				
			}
			return  allquotationList;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * method addQuotation adds a Quotation to the list and database
	 * and returns void 
	 */
	@Override
	public void addQuotation(Quotation quote) throws QuotationExistException 
	{
		if(allquotationList.contains(quote))
		{
			throw new QuotationExistException();
		}
		
		try {
		Connection connection = DBConnection.getConnection();
		String sqlquery="insert into " + TABLEQuotation+" values(?,?,?,?,?,?)";
		
		PreparedStatement pst = connection.prepareStatement(sqlquery);
		pst.setInt(1, quote.getQuoteId());
		pst.setInt(2, quote.getOrder().getOrderId());
		pst.setInt(3, quote.getVendor().getVendorId());
		pst.setInt(4, quote.getQuoteCost());
		pst.setString(5, quote.getEstimateDeliveryDate());
		pst.setInt(6, quote.getQuotedQuantity());
		pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * updateDepartment method  updates particular Department's data
	 * 
	 */
	@Override
	public void updateQuotation(Quotation quote) 
	{
		try
		{
			Connection connection=DBConnection.getConnection();
			String sqlQuery="update "+TABLEQuotation+" set "+COLorderId+" =?, "+COLvendorId+ "=?,"+COLquotedCost+" =?,"+COLestimatedDelivery+" =?,"+COLquotedQuantity+" =?"+" where quoteId="+ quote.getQuoteId() +";";
					
			PreparedStatement pst=connection.prepareStatement(sqlQuery);
	
			pst.setInt(1, quote.getOrder().getOrderId());
			pst.setInt(2,quote.getVendor().getVendorId());
			pst.setInt(3, quote.getQuoteCost());
			pst.setString(4,quote.getEstimateDeliveryDate());
			pst.setInt(5,quote.getQuotedQuantity());

			pst.executeUpdate();
			
			for(Quotation q: allquotationList)
			{
				if(q.getQuoteId()==quote.getQuoteId())
				{
					q.setQuoteId(quote.getQuoteId());
					q.setOrder(quote.getOrder());
					q.setVendor(quote.getVendor());
					q.setQuoteCost(quote.getQuoteCost());
					q.setEstimateDeliveryDate(quote.getEstimateDeliveryDate());
					q.setQuotedQuantity(quote.getQuotedQuantity());
				}
			}

		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
	/**
	 * deleteQuotation method deletes Quote with particular Id
	 */
	@Override
	public void deleteQuotation(Quotation quote)
	{
		try 
		{
			Connection connection = DBConnection.getConnection();
			String sqlquery="delete from "+TABLEQuotation +" where "+COLquoteId +"=?";
			
			PreparedStatement pst = connection.prepareStatement(sqlquery);
			pst.setInt(1, quote.getQuoteId());
			
			pst.executeUpdate();
			allquotationList.remove(quote);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
