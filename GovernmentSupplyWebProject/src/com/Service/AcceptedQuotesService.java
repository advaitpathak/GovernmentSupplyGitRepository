package com.Service;

import java.util.List;

import com.al.dao.AcceptedQuotesDao;
import com.al.dao.OrderDaoImpl;
import com.al.dao.QuotationDao;
import com.al.dao.QuoteExistException;
import com.al.dao.VendorDaoImpl;
import com.al.model.AcceptedQuotes;
import com.al.model.Quotation;

public class AcceptedQuotesService implements AcceptedQuotesServiceInterface {

	@Override
	public List<AcceptedQuotes> getAllAcceptedQuotes() {
		
		return acceptedQuotesDao.getAllAcceptedQuotes();
	}

	@Override
	public void addAcceptedQuote(AcceptedQuotes acceptedQuote) throws QuoteExistException {
		acceptedQuotesDao.addAcceptedQuote(acceptedQuote);
		
	}

	@Override
	public void addAcceptedQuote( int orderId, int vendorId, int quantity, int totalCost) {
		AcceptedQuotes acceptedQuotes = new AcceptedQuotes();
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
