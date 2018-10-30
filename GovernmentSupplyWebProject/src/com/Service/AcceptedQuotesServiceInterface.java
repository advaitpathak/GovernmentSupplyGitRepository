package com.Service;

import java.util.List;

import com.al.dao.AcceptedQuotesDao;
import com.al.dao.AcceptedQuotesDaoImpl;
import com.al.dao.QuoteExistException;
import com.al.model.AcceptedQuotes;

public interface AcceptedQuotesServiceInterface 
{
	AcceptedQuotesDao acceptedQuotesDao = new AcceptedQuotesDaoImpl();
	
	public List<AcceptedQuotes> getAllAcceptedQuotes();
	void addAcceptedQuote(AcceptedQuotes acceptedQuote)throws QuoteExistException;
	void addAcceptedQuote( int orderId, int vendorId, int quantity, int totalCost );
}
