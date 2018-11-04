package com.al.dao;

import java.util.List;

import com.al.model.AcceptedQuotes;
/**
 * 
 * @author Administrator
 *interface @AcceptedQuotesDao 
 */
public interface AcceptedQuotesDao 
{
	String TABLEAcceptedQuotes ="AcceptedQuotes",COLacceptedQuoteId="acceptedQuoteId",COLorderId="orderId",COLvendorId= "vendorId";
	String COLquantity = "quantity",COLtotalCost = "totalCost";

	List<AcceptedQuotes> getAllAcceptedQuotes();
	void addAcceptedQuote(AcceptedQuotes acceptedQuote)throws QuoteExistException;
	
}
