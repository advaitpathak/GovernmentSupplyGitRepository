package com.Service;

import java.util.List;

import com.al.dao.AcceptedQuotesDao;
import com.al.dao.OrderDaoImpl;
import com.al.dao.QuotationDao;
import com.al.dao.QuoteExistException;
import com.al.dao.VendorDaoImpl;
import com.al.model.AcceptedQuotes;
import com.al.model.Quotation;
/**
 * 
 * @author Administrator
 *Class AcceptedQuotesService implements AcceptedQuotesServiceInterface provides service layer functions on AcceptedQuotes Table 
 */
public class AcceptedQuotesService implements AcceptedQuotesServiceInterface {
/**
 * Method @ List<AcceptedQuotes> getAllAcceptedQuotes() 
 * returns List of all accepted Quotes from AcceptedQuotes table through @AcceptedQuotesDao
 */
	@Override
	public List<AcceptedQuotes> getAllAcceptedQuotes() {
		
		return acceptedQuotesDao.getAllAcceptedQuotes();
	}
	/**
	 * Method @ addAcceptedQuote(AcceptedQuotes acceptedQuote)
	 * Add new row of Accepted Quote in AcceptedQuotes table through @AcceptedQuotesDao
	 */
	@Override
	public void addAcceptedQuote(AcceptedQuotes acceptedQuote) throws QuoteExistException {
		acceptedQuotesDao.addAcceptedQuote(acceptedQuote);
		
	}
/**
 * Method @addAcceptedQuote( int orderId, int vendorId, int quantity, int totalCost) 
 * Accepts parameters to create new Entry in AcceptedQuotes tables
 * acceptedQuoteId is incremented every time to avoid primary key violation
 */
	@Override
	public void addAcceptedQuote( int orderId, int vendorId, int quantity, int totalCost) {
		AcceptedQuotes acceptedQuotes = new AcceptedQuotes();
		List<AcceptedQuotes> allAcceptedQuotes = acceptedQuotesDao.getAllAcceptedQuotes();
		if(allAcceptedQuotes.size()==0)
		{
			acceptedQuotes.setAcceptedQuoteId(401);
		}
		else
		{
			AcceptedQuotes acceptedQuotes2 = allAcceptedQuotes.get(allAcceptedQuotes.size()-1);
			int acceptedQuoteId = acceptedQuotes2.getAcceptedQuoteId();
			acceptedQuotes.setAcceptedQuoteId(acceptedQuoteId+1);
		}
		//acceptedQuotes.setAcceptedQuoteId(acceptedQuoteId);
		acceptedQuotes.setOrder(new OrderDaoImpl().getOrder(orderId));
		acceptedQuotes.setVendor(new VendorDaoImpl().getVendor(vendorId));
		acceptedQuotes.setQuantity(quantity);
		acceptedQuotes.setTotalCost(totalCost);
		
		
			try {
				acceptedQuotesDao.addAcceptedQuote(acceptedQuotes);
			} catch (QuoteExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	
}
