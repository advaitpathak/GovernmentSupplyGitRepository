package com.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.al.dao.OrderDao;
import com.al.dao.OrderDaoImpl;
import com.al.dao.QuotationExistException;
import com.al.dao.VendorDao;
import com.al.dao.VendorDaoImpl;
import com.al.model.Quotation;
import com.al.model.Vendor;

class QuotationCompareOnQuotedCost implements Comparator<Quotation>
{
	@Override
	public int compare(Quotation quote1, Quotation quote2)
	{
		return (quote1.getQuoteCost()-quote2.getQuoteCost());
	}
}

class QuotationCompareOnEarliestDelivery implements Comparator<Quotation>
{
	@Override
	public int compare(Quotation quote1, Quotation quote2) 
	{
		return (quote1.getEstimateDeliveryDate().compareTo(quote2.getEstimateDeliveryDate()));		
	}
}

public class QuotationService implements QuotationServiceInterface {

	@Override
	public Quotation getQuotation(int quoteId) {
		// TODO Auto-generated method stub
		return quotationDao.getQuotation(quoteId);
	}

	@Override
	public List<Quotation> getAllQuotation() {
		// TODO Auto-generated method stub
		return quotationDao.getAllQuotation();
	}

	@Override
	public void addQuotation(Quotation quote) throws QuotationExistException {
		// TODO Auto-generated method stub
		quotationDao.addQuotation(quote);
	}

	@Override
	public void updateQuotation(Quotation quote) {
		// TODO Auto-generated method stub
		quotationDao.updateQuotation(quote);
	}

	@Override
	public void deleteQuotation(Quotation quote) {
		// TODO Auto-generated method stub
		quotationDao.deleteQuotation(quote);
	}

	@Override
	public void addQuotation(int quoteId, int orderId, int vendorId, int quotedCost, String estimatedDeliveryDate,
			int quotedQuantity) 
	{
		Quotation quotation = new Quotation();
		quotation.setQuoteId(quoteId);
		OrderDao orderDao = new OrderDaoImpl();
		quotation.setOrder(orderDao.getOrder(orderId));
		VendorDao vendorDao = new VendorDaoImpl();
		quotation.setVendor(vendorDao.getVendor(vendorId));
		quotation.setQuoteCost(quotedCost);
		quotation.setEstimateDeliveryDate(estimatedDeliveryDate);
		quotation.setQuotedQuantity(quotedQuantity);
		
		try
		{
			quotationDao.addQuotation(quotation);
		}
		catch (QuotationExistException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public boolean checkEstimateDate(String estimatedDeliveryDate, String deadline) {
		// TODO Auto-generated method stub
		
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	    Date estimatedDeliveryDateParse;
		Date deadlineDate;
		try
		{
			estimatedDeliveryDateParse = sdf.parse(estimatedDeliveryDate);
			deadlineDate = sdf.parse(deadline);
		
	    long diffInMillies = Math.abs(deadlineDate.getTime() - estimatedDeliveryDateParse.getTime());
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    if(diff<=0)
	    {
	    	return true;
	    }
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Quotation> lowestQuotedCostFisrt(List<Quotation> allQuotationList) {
		// TODO Auto-generated method stub
		allQuotationList.sort(new QuotationCompareOnQuotedCost());
		return allQuotationList;
	}

	@Override
	public List<Quotation> earliestDelivery(List<Quotation> allQuotationList) {
		// TODO Auto-generated method stub
		allQuotationList.sort(new QuotationCompareOnEarliestDelivery());
		return allQuotationList;
	}

	@Override
	public List<Quotation> getQuotationOfVendor(int vendorId) {
		List<Quotation> vendorQuotationList = new ArrayList<>();
		List<Quotation> allQuotationList = quotationDao.getAllQuotation();
		for(Quotation q : allQuotationList)
		{
			if(q.getVendor().getVendorId()==vendorId)
			{
				vendorQuotationList.add(q);
			}
		}
		return vendorQuotationList;
	}

	@Override
	public List<Quotation> getQuotableOrdersForVendor(List<Quotation> allQuotationList, int loggedInVendorId) 
	{
		List<Quotation> quotableOrdersList = new ArrayList<>();
		for(Quotation quotation : allQuotationList)
		{
			if(quotation.getVendor().getVendorId()==loggedInVendorId)
			{
				quotableOrdersList.add(quotation);
			}
		}
		return quotableOrdersList;
	}
}	