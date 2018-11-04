package com.al.dao;

import java.util.List;

import com.al.model.Quotation;
/**
 * 
 * @author Administrator
 *interface @AcceptedQuotesDao 
 */
public interface QuotationDao 
{
	String TABLEQuotation ="Quotation",COLquoteId="quoteId",COLorderId="orderId",COLvendorId= "vendorId";
	String COLquotedCost = "quotedCost",COLestimatedDelivery = "estimatedDelivery", COLquotedQuantity = "quotedQuantity";
	 
	Quotation getQuotation(int quoteId);
	List<Quotation> getAllQuotation();
	void addQuotation(Quotation quote)throws QuotationExistException;
	void updateQuotation(Quotation quote);
	void deleteQuotation(Quotation quote);

}
