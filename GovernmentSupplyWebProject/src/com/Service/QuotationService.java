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
/**
 * 
 * @author Dhanesh
 * Class @QuotationCompareOnQuotedCost implements Comparator<Quotation>
 * Comparator for list of Quote cost of various Quotes submitted by Vendor
 *
 */
class QuotationCompareOnQuotedCost implements Comparator<Quotation>
{
	/**
	 * Method @int compare(Quotation quote1, Quotation quote2) 
	 * this method return int value of 0,>0,<0 after comparing two quote cost
	 * 0 for same quote cost
	 * >0 if quote 1 cost is more than quote 2 cost 
	 * <0 if quote 1 cost is less the quote 2 cost
	 */
	@Override
	public int compare(Quotation quote1, Quotation quote2)
	{
		return (quote1.getQuoteCost()-quote2.getQuoteCost());
	}
}
/**
 * 
 * @author Dhanesh
 * Class @QuotationCompareOnEarliestDelivery implements Comparator<Quotation>
 * This class provides functions to compare Date    
 *
 */
class QuotationCompareOnEarliestDelivery implements Comparator<Quotation>
{
	/**
	 * Method @int compare(Quotation quote1, Quotation quote2) 
	 * this method return int value of 0,>0,<0 after comparing two date
	 * 0 for same date
	 * >0 if quote 1 EstimateDelivery date is after than quote 2 EstimateDelivery date 
	 * <0 if quote 1 EstimateDelivery date is before than quote 2 EstimateDelivery date 
	 */
	@Override
	public int compare(Quotation quote1, Quotation quote2) 
	{
		return (quote1.getEstimateDeliveryDate().compareTo(quote2.getEstimateDeliveryDate()));		
	}
}

/**
 * 
 * @author Dhanesh 
 *Class @QuotationService implements QuotationServiceInterface
 *This class provides service layer functions for Quotation Table
 */
public class QuotationService implements QuotationServiceInterface {

/**
 * Method @Quotation getQuotation(int quoteId)
 * This method accepts the quoteId from user and fetches Quote from Quotation table
 * @return Quotation from the Quotation table through QuotationDao  
 */
	@Override
	public Quotation getQuotation(int quoteId) {
		// TODO Auto-generated method stub
		return quotationDao.getQuotation(quoteId);
	}

	/**
	 * Method @List<Quotation> getAllQuotation()
	 * This method fetches all the Quotatios from Quotation table
	 * @return List of Quotation from the Quotation table through QuotationDao  
	 */
	@Override
	public List<Quotation> getAllQuotation() {
		// TODO Auto-generated method stub
		return quotationDao.getAllQuotation();
	}
	/**
	 * Method @addQuotation(Quotation quote)
	 * This method accepts Quotation from user and adds new Quotation to Quotation table through addQuotation method of QuotationDao  
	 */	
	@Override
	public void addQuotation(Quotation quote) throws QuotationExistException {
		// TODO Auto-generated method stub
		quotationDao.addQuotation(quote);
	}
	
	/**
	 * Method @updateQuotation(Quotation quote)
	 * This method accepts Quotation from user and updates row of that Quotation into Quotation table through updateQuotation of QuotationDao  
	 */	
	@Override
	public void updateQuotation(Quotation quote) {
		// TODO Auto-generated method stub
		quotationDao.updateQuotation(quote);
	}
	/**
	 * Method @deleteQuotation(Quotation quote)
	 * This method accepts Quotation from user and delete row of that Quotation into Quotation table through deleteQuotation of QuotationDao  
	 */	
	@Override
	public void deleteQuotation(Quotation quote) {
		// TODO Auto-generated method stub
		quotationDao.deleteQuotation(quote);
	}

	/**
	 * Method @addQuotation(int quoteId, int orderId, int vendorId, int quotedCost, String estimatedDeliveryDate,int quotedQuantity)
	 *create new Object of Quotation by accepting various parameters from user and adds that Quotation to quotation table through addQuotations Method of QuotationDao
	 */
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
	/**
	 * Method @checkEstimateDate(String estimatedDeliveryDate, String deadline)
	 *For a particular order this method checks if estimatedDeliveryDate is before the deadline date
	 *@return true for estimatedDelieryDate before deadline and vice versa
	 */
	
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
	    if(diff>=0)
	    {
	    	return true;
	    }
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Method @List<Quotation> lowestQuotedCostFisrt(List<Quotation> allQuotationList)
	 *sorts the List of Quotations depending on Quoted cost
	 *@return Sorted list of Quotations
	 */
	@Override
	public List<Quotation> lowestQuotedCostFisrt(List<Quotation> allQuotationList) {
		// TODO Auto-generated method stub
		allQuotationList.sort(new QuotationCompareOnQuotedCost());
		return allQuotationList;
	}

	/**
	 * Method @List<Quotation> earliestDelivery(List<Quotation> allQuotationList)
	 *sorts the List of Quotations depending on DeliveryDate
	 *@return Sorted list of Quotations
	 */
	@Override
	public List<Quotation> earliestDelivery(List<Quotation> allQuotationList) {
		// TODO Auto-generated method stub
		allQuotationList.sort(new QuotationCompareOnEarliestDelivery());
		return allQuotationList;
	}
	
	/**
	 * Method @List<Quotation> getQuotationOfVendor(int vendorId)
	 *This method fetches all Quotations from Quotation table filters that list and adds quotations from Vendor with vendorID to new list of Quotations 
	 *@return Sorted list of Quotations
	 */	
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
/**
 * Meothd @List<Quotation> getQuotedOrdersForVendor(List<Quotation> allQuotationList, int loggedInVendorId)
 * @return List of Quotation consisting Quotations which are submitted by Vendor with vendorId 
 */
	@Override
	public List<Quotation> getQuotedOrdersForVendor(List<Quotation> allQuotationList, int loggedInVendorId) 
	{
		/*Return the list of quotations made by the logged in vendor*/
		List<Quotation> vendorQuotedOrderList = new ArrayList<>();
		for(Quotation quotation : allQuotationList)
		{
			if(quotation.getVendor().getVendorId()==loggedInVendorId)
			{
				vendorQuotedOrderList.add(quotation);
			}
		}
		return vendorQuotedOrderList;
	}
}	